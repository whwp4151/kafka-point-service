package com.sadik.pointapi.point.infrastructure

import com.sadik.pointapi.point.application.adapter.EventPublisher
import com.sadik.pointapi.point.application.dto.PointEarnedEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaEventPublisher(
    private val kafkaTemplate: KafkaTemplate<String, PointEarnedEvent>
) : EventPublisher {

    override fun addPoint(event: PointEarnedEvent
    ) {
        kafkaTemplate.send("point-earned-topic", event.userId.toString(), event)
    }
}