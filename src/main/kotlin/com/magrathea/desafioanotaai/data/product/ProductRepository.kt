package com.magrathea.desafioanotaai.data.product

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : MongoRepository<ProductEntity, String>