package com.magrathea.desafioanotaai.domain.category

import com.magrathea.desafioanotaai.data.category.CategoryRepository
import com.magrathea.desafioanotaai.domain.aws.AwsSnsService
import com.magrathea.desafioanotaai.domain.aws.MessageDTO
import com.magrathea.desafioanotaai.domain.category.exception.CategoryNotFoundException
import com.magrathea.desafioanotaai.mapper.toDTO
import com.magrathea.desafioanotaai.mapper.toEntity
import org.springframework.stereotype.Service

@Service
class CategoryService(
        private val categoryRepository: CategoryRepository,
        private val awsSnsService: AwsSnsService,
) {
    fun create(categoryDto: CategoryDTO): CategoryDTO {
        val categoryEntity = categoryDto.toEntity()
        val createdCategoryEntity = categoryRepository.save(categoryEntity)
        val createdCategoryDTO = createdCategoryEntity.toDTO()
        awsSnsService.publish(MessageDTO(createdCategoryDTO.toString()))

        return createdCategoryDTO
    }

    fun list(): List<CategoryDTO> = categoryRepository.findAll().map { it.toDTO() }

    fun update(categoryId: String, categoryDTO: CategoryDTO): CategoryDTO {
        val categoryEntity = categoryRepository.findById(categoryId).orElseThrow { CategoryNotFoundException() }

        if (categoryDTO.title.isNotEmpty()) categoryEntity.title = categoryDTO.title
        if (categoryDTO.description.isNotEmpty()) categoryEntity.description = categoryDTO.description

        val updatedCategory = categoryRepository.save(categoryEntity)

        return updatedCategory.toDTO()
    }

    fun delete(categoryId: String) {
        val categoryEntity = categoryRepository.findById(categoryId).orElseThrow { CategoryNotFoundException() }

        categoryRepository.delete(categoryEntity)
    }

    fun findById(id: String): CategoryDTO = categoryRepository.findById(id).orElseThrow { CategoryNotFoundException() }.toDTO()
}