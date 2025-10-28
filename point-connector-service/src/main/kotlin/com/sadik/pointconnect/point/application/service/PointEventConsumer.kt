package com.sadik.pointconnect.point.application.service

import com.sadik.pointconnect.point.application.adapter.PointAdapter
import com.sadik.pointconnect.point.application.dto.PointEarnedEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PointEventConsumer(
    private val pointAdapter: PointAdapter
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(
        topics = ["point-earned-topic"],
        groupId = "point-connect-service"
    )
    fun consume(event: PointEarnedEvent) {
        try {
            // 로그 확인
            log.info("[KafkaConsumer] Received event: $event")

            // DB에 update
            pointAdapter.processPoint(event.uuid, LocalDateTime.now())

            log.info("[KafkaConsumer] PointHistory saved for userId=${event.userId}")
        } catch (e: Exception) {
            log.error("[KafkaConsumer] Failed to process event: $event", e)
            // 실패 처리: DLQ 전송, 재시도 등 로직 추가 가능
        }
    }

}