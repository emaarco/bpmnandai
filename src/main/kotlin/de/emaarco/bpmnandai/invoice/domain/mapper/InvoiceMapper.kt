package de.emaarco.bpmnandai.invoice.domain.mapper

import de.emaarco.bpmnandai.invoice.domain.model.Invoice
import de.emaarco.bpmnandai.invoice.infrastructure.entity.InvoiceEntity
import org.mapstruct.Mapper

@Mapper
interface InvoiceMapper {

    fun mapToEntity(invoice: Invoice): InvoiceEntity

    fun mapToModel(entity: InvoiceEntity): Invoice

}