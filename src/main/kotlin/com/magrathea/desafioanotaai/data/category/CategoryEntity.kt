package com.magrathea.desafioanotaai.data.category

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "categories")
class CategoryEntity(
        @Id
        val id: String? = null,
        var title: String,
        var description: String,
        val ownerId: String,
)