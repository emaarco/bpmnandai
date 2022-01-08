package de.emaarco.bpmnandai.invoice.domain.model

import org.json.JSONObject

data class ShoppingCartItem(
    val id: Long,
    val description: String,
    val discount: Double,
    val order: Long,
    val price: Double,
    val quantity: Double,
    val tax: Double,
    val total: Double,
    val unitOfMeasure: String
) {

    constructor(jsonObject: JSONObject): this(
        jsonObject.getLong("id"),
        jsonObject.getString("description"),
        jsonObject.getDouble("discount"),
        jsonObject.getLong("order"),
        jsonObject.getDouble("price"),
        jsonObject.getDouble("quantity"),
        jsonObject.getDouble("tax"),
        jsonObject.getDouble("total"),
        jsonObject.getString("unit_of_measure")
    )

}