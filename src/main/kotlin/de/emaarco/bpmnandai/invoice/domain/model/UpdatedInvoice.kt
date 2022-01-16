package de.emaarco.bpmnandai.invoice.domain.model

data class UpdatedInvoice(
    val invoiceNumber: String,
    val orderNumber: String,
    val date: String,
    val subtotal: Double,
    val shipping: Double,
    val tax: Double,
    val total: Double,
    val vendor: Vendor,
    val cartItems: List<ShoppingCartItem>
)