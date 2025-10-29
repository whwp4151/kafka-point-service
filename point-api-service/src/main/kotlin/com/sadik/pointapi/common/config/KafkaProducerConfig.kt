package com.sadik.pointapi.common.config

import com.sadik.pointapi.point.application.dto.PointEarnedEvent
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer
import kotlin.jvm.java

@Configuration
class KafkaProducerConfig {
    @Bean
    fun producerFactory(): ProducerFactory<String, PointEarnedEvent> {
        val config = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:29092",
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java,

            // ✅ 멱등성 보장 (중복 전송 방지)
            ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG to true,

            // ✅ acks=all: 브로커 리더 + 팔로워 모두 쓰기 완료시 ack
            ProducerConfig.ACKS_CONFIG to "all",

            // ✅ 재시도 횟수 (기본 2147483647)
            ProducerConfig.RETRIES_CONFIG to Int.MAX_VALUE,

            // ✅ 브로커에 동시에 보낼 수 있는 요청 수 제한
            ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION to 5,

            // ✅ 배치 전송으로 성능 개선 (지연 허용시)
            ProducerConfig.LINGER_MS_CONFIG to 10,
            ProducerConfig.BATCH_SIZE_CONFIG to 32_768,

            // ✅ 타임아웃
            ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG to 30_000,
            ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG to 120_000
        )
        return DefaultKafkaProducerFactory(config)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, PointEarnedEvent> =
        KafkaTemplate(producerFactory())
}