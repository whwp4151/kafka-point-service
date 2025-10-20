package com.sadik.pointapi.config

import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.AdminClientConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaHealthConfig {

    @Bean
    fun kafkaAdminClient(): AdminClient {
        val config = mapOf(
            AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:29092"
        )
        return AdminClient.create(config)
    }

}
