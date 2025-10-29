package com.sadik.pointapi.common.config

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaTopicConfig {

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs = mapOf(
            AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:29092"
        )
        return KafkaAdmin(configs)
    }

    @Bean
    fun pointEarnedTopic(): NewTopic =
        TopicBuilder.name("point-earned-topic")
            .partitions(3)
            .replicas(1)
            .compact()
            .build()

    @Bean
    fun pointEarnedDLQTopic(): NewTopic =
        TopicBuilder.name("point-earned-topic.DLT")
            .partitions(3)
            .replicas(1)
            .build()
}