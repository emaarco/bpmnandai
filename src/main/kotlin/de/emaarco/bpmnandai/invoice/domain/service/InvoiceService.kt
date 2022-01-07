package de.emaarco.bpmnandai.invoice.domain.service

import de.emaarco.bpmnandai.invoice.domain.adapter.VeryfiAdapter
import de.emaarco.bpmnandai.invoice.domain.model.Invoice
import org.springframework.stereotype.Service
import java.util.*
import kotlin.system.exitProcess

@Service
class InvoiceService(private val verifyAdapter: VeryfiAdapter) {

    fun extractDataFromInvoice(file: ByteArray) {
        var invoiceAsBase64 = Base64.getEncoder().encodeToString(file)
        invoiceAsBase64 = "data:application/pdf;base64,${invoiceAsBase64}"
        try {
            val jsonResponse = verifyAdapter.processInvoice(invoiceAsBase64, "invoice.pdf")
            val invoice = Invoice(jsonResponse)
            println(invoice)
        } catch (ex: Exception) {
            ex.printStackTrace()
            exitProcess(1)
        }
    }

}