package de.emaarco.bpmnandai.invoice.api.executables

import de.emaarco.bpmnandai.invoice.domain.facade.InvoiceFacade
import org.springframework.stereotype.Component

@Component
class InvoiceExecutables(private val invoiceFacade: InvoiceFacade) {

    /**
     * A service-task that is invoked to extract data from an invoice
     * (...invoice positions and details about the receiver...)
     */
    fun extractDataFromInvoice(invoiceId: String) {
        println("Extract metadata and invoice-positions from provided invoice")
        invoiceFacade.extractDataFromInvoice(invoiceId)
    }

    /**
     * A service-task that executes the payment (...after the invoice got released...)
     */
    fun executePayment(invoiceId: String) {
        println("Received request to execute the payment of invoice '${invoiceId}'")
    }

}