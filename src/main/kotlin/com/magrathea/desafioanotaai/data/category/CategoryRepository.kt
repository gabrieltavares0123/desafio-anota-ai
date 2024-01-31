package com.magrathea.desafioanotaai.data.category

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : MongoRepository<CategoryEntity, String>