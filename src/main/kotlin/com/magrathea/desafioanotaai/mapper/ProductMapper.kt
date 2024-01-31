package com.magrathea.desafioanotaai.mapper

import com.magrathea.desafioanotaai.data.product.ProductEntity
import com.magrathea.desafioanotaai.domain.product.ProductDTO

fun ProductEntity.toDTO() = ProductDTO(
        id = this.id,
        title = this.title,
        description = this.description,
        ownerId = this.ownerId,
        price = this.price,
        categoryId = this.categoryId
)

fun ProductDTO.toEntity() = ProductEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        ownerId = this.ownerId,
        price = this.price,
        categoryId = this.categoryId
)