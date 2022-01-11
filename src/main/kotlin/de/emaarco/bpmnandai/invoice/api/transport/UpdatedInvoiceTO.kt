package de.emaarco.bpmnandai.invoice.api.transport

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Data to update an invoice (before approving) in case the automatic extraction contains errors")
data class UpdatedInvoiceTO(
    val invoiceNumber: String,
    val orderNumber: String,
    val date: String,
    val subtotal: Double,
    val shipping: Double,
    val tax: Double,
    val total: Double,
    val vendor: VendorTO,
    val cartItems: List<ShoppingCartItemTO>
)