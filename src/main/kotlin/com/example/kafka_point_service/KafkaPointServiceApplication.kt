package com.example.kafka_point_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaPointServiceApplication

fun main(args: Array<String>) {
	runApplication<KafkaPointServiceApplication>(*args)
}
