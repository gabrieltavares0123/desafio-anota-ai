package com.magrathea.desafioanotaai.domain.product

import com.magrathea.desafioanotaai.data.product.ProductRepository
import com.magrathea.desafioanotaai.domain.aws.AwsSnsService
import com.magrathea.desafioanotaai.domain.aws.MessageDTO
import com.magrathea.desafioanotaai.domain.category.CategoryService
import com.magrathea.desafioanotaai.domain.category.exception.CategoryNotFoundException
import com.magrathea.desafioanotaai.domain.product.exception.ProductNotFoundException
import com.magrathea.desafioanotaai.mapper.toDTO
import com.magrathea.desafioanotaai.mapper.toEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProductService(
        private val productRepository: ProductRepository,
        private val categoryService: CategoryService,
        private val awsSnsService: AwsSnsService,
) {
    private val logger: Logger = LoggerFactory.getLogger(ProductService::class.java)

    fun create(productDTO: ProductDTO): ProductDTO {
        try {
            categoryService.findById(productDTO.categoryId!!)
        } catch (_: CategoryNotFoundException){
            logger.error("Can't find a category with id ${productDTO.categoryId}.")
        }
        val newProductEntity = productDTO.toEntity()
        val createdProductDTO = productRepository.save(newProductEntity).toDTO()
        awsSnsService.publish(MessageDTO(createdProductDTO.toString()))

        return createdProductDTO
    }

    fun list(): List<ProductDTO> = productRepository.findAll().map { it.toDTO() }

    fun update(productId: String, productDTO: ProductDTO): ProductDTO {
        val productEntity = productRepository.findById(productId).orElseThrow { ProductNotFoundException() }
        if (productDTO.categoryId != null) {
            try {
                val existingCategory = categoryService.findById(productDTO.categoryId)
                if (existingCategory.id != productEntity.categoryId) productEntity.categoryId = existingCategory.id!!
            } catch (_: CategoryNotFoundException) {
                logger.error("Can't find a category with id ${productDTO.categoryId}.")
            }
        }
        if (productDTO.title.isNotEmpty()) productEntity.title = productDTO.title
        if (productDTO.description.isNotEmpty()) productEntity.description = productDTO.description
        if (productDTO.price != productEntity.price) productEntity.price = productDTO.price
        val updatedProductDTO = productRepository.save(productEntity).toDTO()
        awsSnsService.publish(MessageDTO(updatedProductDTO.toString()))

        return updatedProductDTO
    }

    fun delete(categoryId: String) {
        val productEntity = productRepository.findById(categoryId).orElseThrow { CategoryNotFoundException() }
        productRepository.delete(productEntity)
    }
}