package de.emaarco.bpmnandai.invoice.domain.service

import de.emaarco.bpmnandai.invoice.domain.adapter.VeryfiAdapter
import de.emaarco.bpmnandai.invoice.domain.mapper.InvoiceMapper
import de.emaarco.bpmnandai.invoice.domain.model.Invoice
import de.emaarco.bpmnandai.invoice.domain.model.UpdatedInvoice
import de.emaarco.bpmnandai.invoice.infrastructure.repository.InvoiceRepository
import de.emaarco.bpmnandai.shared.exception.ObjectNotFoundException
import mu.KotlinLogging
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.*

@Service
class InvoiceService(
    private val verifyAdapter: VeryfiAdapter,
    private val invoiceRepository: InvoiceRepository,
) {

    private val log = KotlinLogging.logger { }
    private val invoiceMapper = Mappers.getMapper(InvoiceMapper::class.java)

    fun getSpecificInvoice(uploadId: String): Invoice {
        return findInvoice(uploadId)
    }

    fun extractDataFromInvoice(uploadId: String, file: ByteArray) {
        var invoiceAsBase64 = Base64.getEncoder().encodeToString(file)
        invoiceAsBase64 = "data:application/pdf;base64,${invoiceAsBase64}"
        val jsonResponse = verifyAdapter.processInvoice(invoiceAsBase64, "invoice.pdf")
        val extractedInvoice = Invoice(uploadId, jsonResponse)
        saveInvoice(extractedInvoice)
        log.info { "Extracted data from invoice '${uploadId}'" }
    }

    fun approveInvoice(uploadId: String, updatedInvoice: UpdatedInvoice?): Invoice {
        val matchingInvoice = findInvoice(uploadId)
        updatedInvoice?.let { invoiceData -> updateInvoice(matchingInvoice, invoiceData) }
        log.info { "Successfully approved invoice with uploadId '$uploadId'" }
        return matchingInvoice
    }

    fun executePayment(invoiceId: String) {
        log.info { "Successfully executed payment for invoice '$invoiceId'" }
    }

    /* ------------------------- private helper methods ------------------------- */

    private fun updateInvoice(invoice: Invoice, updateData: UpdatedInvoice) {
        invoice.updateInvoice(updateData)
        saveInvoice(invoice)
        log.debug { "Successfully updated invoice: $invoice" }
    }

    private fun findInvoice(uploadId: String): Invoice {
        return invoiceRepository.findByUploadId(uploadId)
            ?.let { invoice -> invoiceMapper.mapToModel(invoice) }
            ?: throw ObjectNotFoundException("Found no invoice for uploadId '$uploadId'")
    }

    private fun saveInvoice(invoice: Invoice) {
        invoiceRepository.save(invoiceMapper.mapToEntity(invoice))
    }

}