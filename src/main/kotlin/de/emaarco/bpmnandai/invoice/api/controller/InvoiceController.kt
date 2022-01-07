package de.emaarco.bpmnandai.invoice.api.controller

import de.emaarco.bpmnandai.invoice.domain.facade.InvoiceFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/invoice")
class InvoiceController(private val invoiceFacade: InvoiceFacade) {

    @PostMapping("/start")
    fun startInvoiceProcess(@RequestPart("invoice") invoice: MultipartFile): ResponseEntity<Void> {
        println("A new invoice got uploaded - processing it!")
        invoiceFacade.startInvoiceProcess(invoice)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/approve/{invoiceId}")
    fun approveInvoice(@PathVariable("invoiceId") invoiceId: String): ResponseEntity<Void> {
        println("Received request to approve invoice '$invoiceId'")
        invoiceFacade.approveInvoice(invoiceId)
        return ResponseEntity.ok().build()
    }

}