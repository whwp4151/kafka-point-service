package com.sadik.pointapi.point.application.usecase

import com.sadik.pointapi.point.application.type.PointType

interface PointUseCase {
    fun addPoint(userId: Long, pointType: PointType)
}