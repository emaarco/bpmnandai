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

    @PostMapping("/approve")
    fun approveInvoice(@PathVariable(name = "invoiceId") invoiceId: String): ResponseEntity<Void> {
        invoiceFacade.approveInvoice(invoiceId)
        return ResponseEntity.ok().build()
    }

}