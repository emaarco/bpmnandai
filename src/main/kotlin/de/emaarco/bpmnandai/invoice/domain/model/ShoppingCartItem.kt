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

    constructor(jsonObject: JSONObject) : this(
        jsonObject.optLong("id"),
        jsonObject.optString("description"),
        jsonObject.optDouble("discount"),
        jsonObject.optDouble("price"),
        jsonObject.optDouble("quantity"),
        jsonObject.optDouble("tax"),
        jsonObject.optDouble("total"),
        jsonObject.optString("unit_of_measure")
    )

}