package com.sadik.pointapi.point.application.usecase

import com.sadik.pointapi.point.application.dto.PointHistoryDto
import com.sadik.pointapi.point.application.type.PointType
import java.time.LocalDate

interface PointUseCase {
    fun addPoint(userId: Long, pointType: PointType): Boolean
    fun getPointHistory(userId: Long, pointType: PointType, begin: LocalDate, now: LocalDate): List<PointHistoryDto>
}