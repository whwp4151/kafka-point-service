package com.sadik.pointconnect.point.infrastructure.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface PointHistoryJpaRepository: JpaRepository<PointHistoryEntity, Long> {
}