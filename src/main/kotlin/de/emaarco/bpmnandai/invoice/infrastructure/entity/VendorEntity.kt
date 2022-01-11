package de.emaarco.bpmnandai.invoice.infrastructure.entity

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class VendorEntity(

    @Column(name = "vendor_address")
    var address: String,

    @Column(name = "vendor_email")
    var email: String,

    @Column(name = "vendor_name")
    var name: String,

    @Column(name = "vendor_iban")
    val iban: String

)