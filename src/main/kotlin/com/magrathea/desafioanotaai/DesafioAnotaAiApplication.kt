package com.magrathea.desafioanotaai

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class DesafioAnotaAiApplication

fun main(args: Array<String>) {
	runApplication<DesafioAnotaAiApplication>(*args)
}
