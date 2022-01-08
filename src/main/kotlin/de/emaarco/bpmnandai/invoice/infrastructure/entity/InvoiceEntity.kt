package de.emaarco.bpmnandai.invoice.infrastructure.entity

import javax.persistence.*

@Entity(name = "invoice_")
data class InvoiceEntity(
    
    @Id
    val invoiceNumber: String,

    val orderNumber: String,
    val date: String,
    val subtotal: Double,
    val shipping: Double,
    val tax: Double,
    val total: Double,
    val vendorIban: String,

    @Embedded
    val vendor: VendorEntity,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "invoice_id")
    val cartItems: MutableList<ShoppingCartItemEntity>

)