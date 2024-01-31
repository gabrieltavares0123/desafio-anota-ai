package com.magrathea.desafioanotaai.domain.category

import org.json.JSONObject

data class CategoryDTO(
        val id: String? = null,
        val title: String,
        val description: String,
        val ownerId: String,
) {
    override fun toString(): String {
        val json = JSONObject()
        json.put("id", this.id)
        json.put("title", this.title)
        json.put("description", this.description)
        json.put("ownerId", this.ownerId)
        json.put("type", "category")

        return json.toString()
    }
}