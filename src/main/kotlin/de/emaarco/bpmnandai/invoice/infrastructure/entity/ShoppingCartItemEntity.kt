package de.emaarco.bpmnandai.invoice.infrastructure.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "shopping_cart_item_")
data class ShoppingCartItemEntity(
    @Id
    private val id: Long,
    private val description: String,
    private val discount: Double,
    private val order: Long,
    private val price: Double,
    private val quantity: Double,
    private val tax: Double,
    private val total: Double,
    private val unitOfMeasure: String,
)