package com.sadik.pointapi.point.application.usecase

import com.sadik.pointapi.point.application.dto.PointHistoryDto
import com.sadik.pointapi.point.application.dto.PointState
import com.sadik.pointapi.point.application.dto.PointSummaryDto
import com.sadik.pointapi.point.application.type.PointType
import java.time.LocalDate

interface PointUseCase {
    fun grantPoint(userId: Long, pointType: PointType): Boolean
    fun grantConditionalPoint(userId: Long, pointType: PointType): Boolean
    fun getPointState(userId: Long, pointType: PointType): PointState
    fun getPointHistory(userId: Long, pointType: PointType, begin: LocalDate, now: LocalDate): List<PointHistoryDto>
    fun getPointSummary(userId: Long): PointSummaryDto
    fun findByUserId(userId: Long): List<PointHistoryDto>
}