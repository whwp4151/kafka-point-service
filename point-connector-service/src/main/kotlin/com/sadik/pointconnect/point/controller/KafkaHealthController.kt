package com.sadik.pointconnect.point.controller

import org.apache.kafka.clients.admin.AdminClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class KafkaHealthController(
    private val kafkaAdminClient: AdminClient
) {
    @GetMapping("/health/kafka")
    fun checkKafka(): Map<String, Any> {
        return try {
            val nodes = kafkaAdminClient.describeCluster().nodes().get()
            mapOf(
                "status" to "UP",
                "nodes" to nodes.map { "${it.host()}:${it.port()}" }
            )
        } catch (ex: Exception) {
            mapOf(
                "status" to "DOWN",
                "error" to (ex.message ?: "Unknown error")
            )
        }
    }
}
