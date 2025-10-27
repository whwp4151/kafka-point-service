package com.sadik.pointapi.point.infrastructure

import com.sadik.pointapi.point.application.adapter.PointAdapter
import com.sadik.pointapi.point.infrastructure.jpa.PointHistoryJpaRepository
import org.springframework.stereotype.Repository

@Repository
class PointRepository(
    private val pointHistoryJpaRepository: PointHistoryJpaRepository
) : PointAdapter {
}