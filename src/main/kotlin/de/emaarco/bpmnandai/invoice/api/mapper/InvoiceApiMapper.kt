package de.emaarco.bpmnandai.invoice.api.mapper

import de.emaarco.bpmnandai.invoice.api.transport.InvoiceTO
import de.emaarco.bpmnandai.invoice.api.transport.UpdatedInvoiceTO
import de.emaarco.bpmnandai.invoice.domain.model.Invoice
import de.emaarco.bpmnandai.invoice.domain.model.UpdatedInvoice
import org.mapstruct.Mapper

@Mapper
interface InvoiceApiMapper {

    fun mapToTO(invoice: Invoice): InvoiceTO

    fun mapToUpdatedInvoice(invoiceTO: UpdatedInvoiceTO): UpdatedInvoice

}