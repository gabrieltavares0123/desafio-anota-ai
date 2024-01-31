package com.magrathea.desafioanotaai.controller.product

import com.magrathea.desafioanotaai.domain.product.ProductDTO
import com.magrathea.desafioanotaai.domain.product.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/product")
class ProductController(
        private val productService: ProductService,
) {
    @PostMapping
    fun create(
            @RequestBody productDTO: ProductDTO
    ): ResponseEntity<ProductDTO> {
        val newCategory = productService.create(productDTO)
        return ResponseEntity.ok().body(newCategory)
    }

    @GetMapping
    fun list(): ResponseEntity<List<ProductDTO>> {
        val productsList = productService.list()
        return ResponseEntity.ok().body(productsList)
    }

    @PutMapping("/{id}")
    fun update(
            @PathVariable("id") productId: String,
            @RequestBody productDTO: ProductDTO,
    ): ResponseEntity<ProductDTO> {
        val updatedProduct = productService.update(productId, productDTO)
        return ResponseEntity.ok().body(updatedProduct)
    }

    @DeleteMapping("/{id}")
    fun delete(
            @PathVariable("id") productId: String,
    ): ResponseEntity<ProductDTO> {
        productService.delete(productId)
        return ResponseEntity.noContent().build()
    }
}