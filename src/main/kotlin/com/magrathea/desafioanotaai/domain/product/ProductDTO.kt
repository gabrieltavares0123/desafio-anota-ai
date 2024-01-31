package com.magrathea.desafioanotaai.domain.product

import org.json.JSONObject

data class ProductDTO(
        val id: String? = null,
        val title: String,
        val description: String,
        val ownerId: String,
        val price: Float,
        val categoryId: String? = null,
) {
    override fun toString(): String {
        val json = JSONObject()
        json.put("id", this.id)
        json.put("title", this.title)
        json.put("description", this.description)
        json.put("ownerId", this.ownerId)
        json.put("categoryId", this.categoryId)
        json.put("price", this.price)
        json.put("type", "product")

        return json.toString()
    }
}