package com.sadik.pointapi.point.infrastructure

import org.springframework.data.jpa.repository.JpaRepository

interface PointHistoryJpaRepository: JpaRepository<PointHistoryEntity, Long> {
}