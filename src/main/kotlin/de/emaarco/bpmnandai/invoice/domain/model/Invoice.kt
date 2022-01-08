package de.emaarco.bpmnandai.invoice.domain.model

import de.emaarco.bpmnandai.shared.annotations.Default
import org.json.JSONArray
import org.json.JSONObject

data class Invoice @Default constructor(
    val invoiceNumber: String,
    val orderNumber: String,
    val date: String,
    val cartItems: MutableList<ShoppingCartItem>,
    val vendor: Vendor,
    val vendorIban: String,
    val subtotal: Double,
    val shipping: Double,
    val tax: Double,
    val total: Double,
) {

    constructor(jsonObject: JSONObject): this(
        jsonObject.getString("invoice_number"),
        jsonObject.getString("purchase_order_number"),
        jsonObject.getString("date"),
        ArrayList(),
        Vendor(jsonObject.getJSONObject("vendor")),
        jsonObject.getString("vendor_iban"),
        jsonObject.getDouble("subtotal"),
        jsonObject.getDouble("shipping"),
        jsonObject.getDouble("tax"),
        jsonObject.getDouble("total")
    ) {
        initShoppingCart(jsonObject.getJSONArray("line_items"))
    }

    private fun initShoppingCart(jsonShoppingCart: JSONArray) {
        for (i in 0 until jsonShoppingCart.length()) {
            val item = jsonShoppingCart.get(i)
            item is JSONObject && cartItems.add(ShoppingCartItem(item));
        }
    }

}