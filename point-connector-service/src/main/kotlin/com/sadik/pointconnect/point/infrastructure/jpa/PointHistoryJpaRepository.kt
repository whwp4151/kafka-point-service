package com.sadik.pointconnect.point.infrastructure.jpa

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface PointHistoryJpaRepository: JpaRepository<PointHistoryEntity, Long> {
    fun findByUuid(uuid: UUID): Optional<PointHistoryEntity>
}