package com.sadik.pointconnect.point.infrastructure

import com.sadik.pointconnect.point.application.adapter.PointAdapter
import com.sadik.pointconnect.point.infrastructure.jpa.PointHistoryJpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.UUID

@Repository
class PointRepository(
    private val pointHistoryJpaRepository: PointHistoryJpaRepository
) : PointAdapter {

    override fun processPoint(uuid: UUID, processdAt: LocalDateTime) {
        val pointHistoryEntity = pointHistoryJpaRepository.findByUuid(uuid)
            .orElseThrow { IllegalArgumentException("Point History not found: $uuid") }

        pointHistoryEntity.processe(processdAt)

        pointHistoryJpaRepository.save(pointHistoryEntity)
    }

}