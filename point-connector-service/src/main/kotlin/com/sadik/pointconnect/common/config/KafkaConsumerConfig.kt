package com.sadik.pointconnect.common.config

import com.sadik.pointconnect.point.application.dto.PointEventDto
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
@EnableKafka
class KafkaConsumerConfig {

    @Bean
    fun consumerFactory(): ConsumerFactory<String, PointEventDto> {
        val config = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:29092",
            ConsumerConfig.GROUP_ID_CONFIG to "point-connect-group",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java,
            JsonDeserializer.TRUSTED_PACKAGES to "*"
        )
        return DefaultKafkaConsumerFactory(config)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, PointEventDto> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, PointEventDto>()
        factory.consumerFactory = consumerFactory()
        return factory
    }

    @Bean
    fun kafkaAdminClient(): AdminClient {
        val config = mapOf(
            AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:29092"
        )
        return AdminClient.create(config)
    }

}