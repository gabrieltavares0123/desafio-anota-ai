package com.magrathea.desafioanotaai.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory

@Configuration
class MongoDBConfig {
    @Bean
    fun mongoDatabaseFactory(): SimpleMongoClientDatabaseFactory {
        return SimpleMongoClientDatabaseFactory("mongodb://localhost:27017/products-catalog")
    }

    @Bean
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongoDatabaseFactory())
    }
}