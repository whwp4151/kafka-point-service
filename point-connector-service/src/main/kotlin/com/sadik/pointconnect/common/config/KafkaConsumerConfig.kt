package com.sadik.pointconnect.common.config

import com.sadik.pointconnect.point.application.dto.PointEarnedEvent
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer
import org.springframework.kafka.listener.DefaultErrorHandler
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer
import org.springframework.util.backoff.FixedBackOff

@Configuration
@EnableKafka
class KafkaConsumerConfig {

    @Bean
    fun consumerFactory(): ConsumerFactory<String, PointEarnedEvent> {
        val config = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:29092",
            ConsumerConfig.GROUP_ID_CONFIG to "point-connect-service",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java,

            // 오프셋 자동 커밋 방지 (수동 커밋으로 제어)
            ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to false,

            // 메시지 처리 실패 시, 재시작 간격
            ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG to 30_000,
            ConsumerConfig.MAX_POLL_RECORDS_CONFIG to 10,

            // 중복 데이터 허용 최소화
            ConsumerConfig.ISOLATION_LEVEL_CONFIG to "read_committed"
        )

        val jsonDeserializer = JsonDeserializer(PointEarnedEvent::class.java, false)
        jsonDeserializer.addTrustedPackages("*")

        return DefaultKafkaConsumerFactory(config, StringDeserializer(), jsonDeserializer)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, PointEarnedEvent> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, PointEarnedEvent>()
        factory.consumerFactory = consumerFactory()

        // 예외 발생 시 DLQ로 전송
        factory.setCommonErrorHandler(
            DefaultErrorHandler(
                DeadLetterPublishingRecoverer(kafkaTemplate()),  // DLQ 발행기
                FixedBackOff(1000L, 3L)  // 1초 간격, 최대 3번 재시도 후 DLQ 이동
            )
        )

        // 오프셋 커밋 수동
        factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL

        return factory
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, PointEarnedEvent> =
        KafkaTemplate(
            DefaultKafkaProducerFactory(
                mapOf(
                    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:29092",
                    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
                    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java
                )
            )
        )

}