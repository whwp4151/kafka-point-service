package com.sadik.pointapi.point.application.service.condition

import com.sadik.pointapi.point.application.dto.PointState
import com.sadik.pointapi.point.application.type.PointType
import com.sadik.pointapi.point.application.usecase.PointUseCase

interface IPointCondition {
    fun calcPoint(userId: Long, pointUseCase: PointUseCase): Long
    fun getPointType(): PointType
    fun getPointState(userId: Long, pointUseCase: PointUseCase): PointState
}