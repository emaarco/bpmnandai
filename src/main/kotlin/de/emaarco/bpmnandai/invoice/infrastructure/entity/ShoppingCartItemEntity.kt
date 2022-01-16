package de.emaarco.bpmnandai.invoice.infrastructure.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "shopping_cart_item_")
data class ShoppingCartItemEntity(
    @Id
    val id: Long,
    val description: String,
    val discount: Double,
    val price: Double,
    val quantity: Double,
    val tax: Double,
    val total: Double,
    val unitOfMeasure: String,
)