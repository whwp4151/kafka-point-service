package com.sadik.pointapi.point.infrastructure

import com.sadik.pointapi.point.application.adapter.EventPublisher
import com.sadik.pointapi.point.application.dto.PointEarnedEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.support.TransactionSynchronization
import org.springframework.transaction.support.TransactionSynchronizationManager

@Component
class KafkaEventPublisher(
    private val kafkaTemplate: KafkaTemplate<String, PointEarnedEvent>
) : EventPublisher {

    override fun addPointAfterCommit(event: PointEarnedEvent) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            // 트랜잭션 커밋 이후 발행
            TransactionSynchronizationManager.registerSynchronization(object : TransactionSynchronization {
                override fun afterCommit() {
                    kafkaTemplate.send("point-earned-topic", event.userId.toString(), event)
                }
            })
        } else {
            // 트랜잭션 범위 밖이면 즉시 발행
            kafkaTemplate.send("point-earned-topic", event.userId.toString(), event)
        }
    }
}