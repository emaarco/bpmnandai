package de.emaarco.bpmnandai.invoice.domain.facade

import de.emaarco.bpmnandai.camunda.domain.ProcessService
import de.emaarco.bpmnandai.invoice.domain.service.InvoiceService
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Component
class InvoiceFacade(private val invoiceService: InvoiceService, private val processService: ProcessService) {

    private val processKey = "Prozess_RechnungBezahlen"

    fun startInvoiceProcess(invoice: MultipartFile) {
        val invoiceId = UUID.randomUUID().toString()
        val processVariables: MutableMap<String, Any> = HashMap()
        processVariables["invoiceId"] = invoiceId
        processVariables["document"] = invoice.bytes
        processService.startInstanceOfProcess(processKey, invoiceId, processVariables)
    }

    fun extractDataFromInvoice(invoiceId: String) {
        val invoice = processService.getProcessVariable(invoiceId, "document")
        invoiceService.extractDataFromInvoice(invoice as ByteArray)
    }

    fun approveInvoice(invoiceId: String) {
        val approveTask = processService.getTaskForBusinessKey(invoiceId)
        val taskResult: MutableMap<String, Any> = HashMap();
        taskResult["approved"] = true
        processService.finishTask(approveTask.id, taskResult)
    }

}