package de.emaarco.bpmnandai.invoice.domain.model

import org.json.JSONObject

class Invoice(jsonObject: JSONObject) {

    private val invoiceNumber: String
    private val orderNumber: String
    private val date: String
    private val cartItems: MutableList<ShoppingCartItem>
    private val vendor: Vendor
    private val vendorIban: String
    private val subtotal: Double
    private val shipping: Double
    private val tax: Double
    private val total: Double

    init {
        this.invoiceNumber = jsonObject.getString("invoice_number")
        this.orderNumber = jsonObject.getString("purchase_order_number")
        this.date = jsonObject.getString("date")
        this.vendorIban = jsonObject.getString("vendor_iban")
        this.subtotal = jsonObject.getDouble("subtotal")
        this.shipping = jsonObject.getDouble("shipping")
        this.tax = jsonObject.getDouble("tax")
        this.total = jsonObject.getDouble("total")
        val rawVendor: JSONObject = jsonObject.getJSONObject("vendor")
        this.vendor = Vendor(rawVendor)
        this.cartItems = ArrayList()
        val allItems = jsonObject.getJSONArray("line_items")
        for (i in 0 until allItems.length()) {
            val item = allItems.get(i)
            item is JSONObject && cartItems.add(ShoppingCartItem(item));
        }
    }

}