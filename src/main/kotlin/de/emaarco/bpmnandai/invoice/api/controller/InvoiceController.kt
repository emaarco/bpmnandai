package de.emaarco.bpmnandai.invoice.api.controller

import de.emaarco.bpmnandai.invoice.api.mapper.InvoiceApiMapper
import de.emaarco.bpmnandai.invoice.api.transport.InvoiceTO
import de.emaarco.bpmnandai.invoice.api.transport.UpdatedInvoiceTO
import de.emaarco.bpmnandai.invoice.domain.facade.InvoiceFacade
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KotlinLogging
import org.mapstruct.factory.Mappers
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/invoice")
@Tag(name = "Invoice Controller")
class InvoiceController(private val invoiceFacade: InvoiceFacade) {

    private val log = KotlinLogging.logger {}
    private val invoiceMapper = Mappers.getMapper(InvoiceApiMapper::class.java)

    @GetMapping("/{uploadId}")
    @Operation(description = "Get information to a specific invoice")
    fun getSpecificInvoice(@PathVariable("uploadId") uploadId: String): ResponseEntity<InvoiceTO> {
        log.debug { "Received request to get information to invoice '$uploadId'" }
        val matchingInvoice = invoiceFacade.getInvoice(uploadId)
        return ResponseEntity.ok().body(invoiceMapper.mapToTO(matchingInvoice))
    }

    @PostMapping("/upload")
    @Operation(description = "Upload a new invoice to start the payment process")
    fun uploadInvoice(@RequestPart("invoice") invoice: MultipartFile): ResponseEntity<String> {
        log.debug { "A new invoice got uploaded - processing it!" }
        return ResponseEntity.ok().body(invoiceFacade.uploadInvoice(invoice))
    }

    @PostMapping("/approve/{uploadId}")
    @Operation(description = "Approve the invoice to trigger the payment")
    fun approveInvoice(
        @PathVariable("uploadId") uploadId: String,
        @RequestBody invoice: UpdatedInvoiceTO?
    ): ResponseEntity<InvoiceTO> {
        log.debug { "Received request to approve invoice '$uploadId': $invoice" }
        val updatedInvoice = invoice?.let { inv -> invoiceMapper.mapToUpdatedInvoice(inv) }
        val approvedInvoice = invoiceFacade.approveInvoice(uploadId, updatedInvoice)
        return ResponseEntity.ok().body(invoiceMapper.mapToTO(approvedInvoice))
    }

}