package de.emaarco.bpmnandai.invoice.domain.model

import org.json.JSONObject

class Vendor(jsonObject: JSONObject) {

    private val address: String;
    private val email: String;
    private val name: String;

    init {
        this.address = jsonObject.getString("address")
        this.email = jsonObject.getString("email")
        this.name = jsonObject.getString("name")
    }

}