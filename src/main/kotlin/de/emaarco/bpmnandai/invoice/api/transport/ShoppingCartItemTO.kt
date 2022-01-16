package de.emaarco.bpmnandai.invoice.api.transport

data class ShoppingCartItemTO(
    val id: Long,
    val description: String,
    val discount: Double,
    val price: Double,
    val quantity: Double,
    val tax: Double,
    val total: Double,
    val unitOfMeasure: String,
)