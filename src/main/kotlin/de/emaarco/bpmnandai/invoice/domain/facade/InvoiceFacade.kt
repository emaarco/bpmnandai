package de.emaarco.bpmnandai.invoice.domain.facade

import de.emaarco.bpmnandai.camunda.domain.ProcessService
import de.emaarco.bpmnandai.invoice.domain.model.Invoice
import de.emaarco.bpmnandai.invoice.domain.model.UpdatedInvoice
import de.emaarco.bpmnandai.invoice.domain.service.InvoiceService
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Component
class InvoiceFacade(private val invoiceService: InvoiceService, private val processService: ProcessService) {

    private val processKey = "Prozess_RechnungBezahlen"

    fun getInvoice(uploadId: String): Invoice {
        return invoiceService.getSpecificInvoice(uploadId)
    }

    fun uploadInvoice(invoice: MultipartFile): String {
        val uploadId = UUID.randomUUID().toString()
        val processVariables: MutableMap<String, Any> = HashMap()
        processVariables["uploadId"] = uploadId
        processVariables["document"] = invoice.bytes
        processService.startInstanceOfProcess(processKey, uploadId, processVariables)
        return uploadId
    }

    fun extractDataFromInvoice(uploadId: String) {
        val invoice = processService.getProcessVariable(uploadId, "document")
        invoiceService.extractDataFromInvoice(uploadId, invoice as ByteArray)
    }

    fun approveInvoice(uploadId: String, invoice: UpdatedInvoice?): Invoice {
        val approveTask = processService.getTaskForBusinessKey(uploadId)
        val approvedInvoice = invoiceService.approveInvoice(uploadId, invoice)
        processService.finishTask(approveTask.id)
        return approvedInvoice
    }

    fun executePayment(uploadId: String) {
        invoiceService.executePayment(uploadId)
    }

}