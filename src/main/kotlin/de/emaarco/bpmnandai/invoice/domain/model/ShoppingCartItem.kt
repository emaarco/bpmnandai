package de.emaarco.bpmnandai.invoice.domain.model

import de.emaarco.bpmnandai.shared.annotations.Default
import org.json.JSONObject

data class ShoppingCartItem @Default constructor(
    val id: Long,
    val description: String,
    val discount: Double,
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
        jsonObject.getDouble("price"),
        jsonObject.getDouble("quantity"),
        jsonObject.getDouble("tax"),
        jsonObject.getDouble("total"),
        jsonObject.getString("unit_of_measure")
    )

}