package de.emaarco.bpmnandai.invoice.infrastructure.repository

import de.emaarco.bpmnandai.invoice.infrastructure.entity.InvoiceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface InvoiceRepository : JpaRepository<InvoiceEntity, String> {

    fun findByUploadId(uploadId: String): InvoiceEntity?

}