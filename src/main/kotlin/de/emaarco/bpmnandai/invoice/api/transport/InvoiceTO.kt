package de.emaarco.bpmnandai.invoice.api.transport

data class InvoiceTO(
    val uploadId: String,
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