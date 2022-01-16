package de.emaarco.bpmnandai.invoice.domain.model

import de.emaarco.bpmnandai.shared.annotations.Default
import org.json.JSONArray
import org.json.JSONObject

data class Invoice @Default constructor(
    val uploadId: String,
    var invoiceNumber: String,
    var orderNumber: String,
    var date: String,
    val cartItems: MutableList<ShoppingCartItem>,
    var vendor: Vendor,
    var subtotal: Double,
    var shipping: Double,
    var tax: Double,
    var total: Double,
) {

    constructor(uploadId: String, jsonObject: JSONObject) : this(
        uploadId,
        jsonObject.getString("invoice_number"),
        jsonObject.getString("purchase_order_number"),
        jsonObject.getString("date"),
        ArrayList(),
        Vendor(jsonObject.getJSONObject("vendor"), jsonObject.getString("vendor_iban")),
        jsonObject.getDouble("subtotal"),
        jsonObject.getDouble("shipping"),
        jsonObject.getDouble("tax"),
        jsonObject.getDouble("total")
    ) {
        initShoppingCart(jsonObject.getJSONArray("line_items"))
    }

    fun updateInvoice(updatedInvoice: UpdatedInvoice) {
        this.invoiceNumber = updatedInvoice.invoiceNumber
        this.orderNumber = updatedInvoice.orderNumber
        this.date = updatedInvoice.date
        this.subtotal = updatedInvoice.subtotal
        this.shipping = updatedInvoice.shipping
        this.tax = updatedInvoice.tax
        this.total = updatedInvoice.total
        this.vendor = updatedInvoice.vendor
        this.cartItems.clear()
        this.cartItems.addAll(updatedInvoice.cartItems)
    }

    private fun initShoppingCart(jsonShoppingCart: JSONArray) {
        for (i in 0 until jsonShoppingCart.length()) {
            val item = jsonShoppingCart.get(i)
            item is JSONObject && cartItems.add(ShoppingCartItem(item));
        }
    }

}