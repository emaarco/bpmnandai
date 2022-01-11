package de.emaarco.bpmnandai.invoice.domain.model

import de.emaarco.bpmnandai.shared.annotations.Default
import org.json.JSONObject

data class Vendor @Default constructor(
    val address: String,
    val email: String,
    val name: String,
    val iban: String,
) {

    constructor(jsonObject: JSONObject, vendorIban: String) : this(
        jsonObject.getString("address"),
        jsonObject.getString("email"),
        jsonObject.getString("name"),
        vendorIban
    )

}