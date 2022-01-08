package de.emaarco.bpmnandai.invoice.infrastructure.entity

import javax.persistence.Embeddable

@Embeddable
data class VendorEntity(
    var address: String,
    var email: String,
    var name: String,
)