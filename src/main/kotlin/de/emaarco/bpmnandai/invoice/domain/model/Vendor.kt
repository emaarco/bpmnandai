package de.emaarco.bpmnandai.invoice.domain.model

import org.json.JSONObject

data class Vendor(val address: String, val email: String, val name: String) {

    constructor(jsonObject: JSONObject): this(
        jsonObject.getString("address"),
        jsonObject.getString("email"),
        jsonObject.getString("name")
    )

}