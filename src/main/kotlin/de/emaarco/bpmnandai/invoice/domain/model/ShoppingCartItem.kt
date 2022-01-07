package de.emaarco.bpmnandai.invoice.domain.model

import org.json.JSONObject

class ShoppingCartItem(jsonObject: JSONObject) {

    private val id: Long
    private val description: String
    private val discount: Double
    private val order: Long
    private val price: Double
    private val quantity: Double
    private val tax: Double
    private val total: Double
    private val unitOfMeasure: String

    init {
        this.id = jsonObject.getLong("id")
        this.description = jsonObject.getString("description")
        this.discount = jsonObject.getDouble("discount")
        this.order = jsonObject.getLong("order")
        this.price = jsonObject.getDouble("price")
        this.quantity = jsonObject.getDouble("quantity")
        this.tax = jsonObject.getDouble("tax")
        this.total = jsonObject.getDouble("total")
        this.unitOfMeasure = jsonObject.getString("unit_of_measure")
    }

}