package com.magrathea.desafioanotaai.mapper

import com.magrathea.desafioanotaai.data.category.CategoryEntity
import com.magrathea.desafioanotaai.domain.category.CategoryDTO

fun CategoryDTO.toEntity() = CategoryEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        ownerId = this.ownerId,
)

fun CategoryEntity.toDTO() = CategoryDTO(
        id = this.id,
        title = this.title,
        description = this.description,
        ownerId = this.ownerId,
)

