package com.magrathea.desafioanotaai.controller.category

import com.magrathea.desafioanotaai.domain.category.CategoryDTO
import com.magrathea.desafioanotaai.domain.category.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/category")
class CategoryController(
        private val categoryService: CategoryService,
) {
    @PostMapping
    fun create(
            @RequestBody categoryDTO: CategoryDTO
    ): ResponseEntity<CategoryDTO> {
        val newCategory = categoryService.create(categoryDTO)
        return ResponseEntity.ok().body(newCategory)
    }

    @GetMapping
    fun list(): ResponseEntity<List<CategoryDTO>> {
        val categoriesList = categoryService.list()
        return ResponseEntity.ok().body(categoriesList)
    }

    @PutMapping("/{id}")
    fun update(
            @PathVariable("id") categoryId: String,
            @RequestBody categoryDTO: CategoryDTO,
    ): ResponseEntity<CategoryDTO> {
        val updatedCategory = categoryService.update(categoryId, categoryDTO)
        return ResponseEntity.ok().body(updatedCategory)
    }

    @DeleteMapping("/{id}")
    fun update(
            @PathVariable("id") categoryId: String,
    ): ResponseEntity<CategoryDTO> {
        categoryService.delete(categoryId)
        return ResponseEntity.noContent().build()
    }
}