package de.emaarco.bpmnandai.invoice.api.transport

data class VendorTO(
    val address: String,
    val email: String,
    val name: String,
    val iban: String,
)