package de.emaarco.bpmnandai.invoice.api.executables

import de.emaarco.bpmnandai.invoice.domain.facade.InvoiceFacade
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class InvoiceExecutables(private val invoiceFacade: InvoiceFacade) {

    private val log = KotlinLogging.logger {}

    /**
     * A service-task that is invoked to extract data from an invoice
     * (...invoice positions and details about the receiver...)
     */
    fun extractDataFromInvoice(invoiceId: String) {
        log.info { "Extract metadata and invoice-positions from provided invoice" }
        invoiceFacade.extractDataFromInvoice(invoiceId)
    }
    
    /**
     * A service-task that executes the payment (...after the invoice got released...)
     */
    fun executePayment(invoiceId: String) {
        log.info { "Received request to execute the payment of invoice '$invoiceId'" }
        invoiceFacade.executePayment(invoiceId)
    }

}