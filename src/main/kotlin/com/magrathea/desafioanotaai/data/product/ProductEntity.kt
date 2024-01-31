package com.magrathea.desafioanotaai.data.product

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "products")
class ProductEntity(
        @Id
        val id: String? = null,
        var title: String,
        var description: String,
        val ownerId: String,
        var price: Float,
        var categoryId: String? = null,
)