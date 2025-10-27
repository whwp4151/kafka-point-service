package com.sadik.pointapi.point.application.service.condition

import com.sadik.pointapi.point.application.dto.PointState
import com.sadik.pointapi.point.application.service.PointService
import com.sadik.pointapi.point.application.type.PointType

interface IPointCondition {
    fun calcPoint(userId: Long, pointService: PointService): Long
    fun getPointType(): PointType
    fun getPointState(userId: Long, pointService: PointService): PointState
}