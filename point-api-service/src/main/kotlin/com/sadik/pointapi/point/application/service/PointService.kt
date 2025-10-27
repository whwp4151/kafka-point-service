package com.sadik.pointapi.point.application.service

import com.sadik.pointapi.point.application.type.PointType
import com.sadik.pointapi.point.application.usecase.PointUseCase
import jakarta.transaction.Transactional

class PointService() : PointUseCase {

    @Transactional
    override fun addPoint(userId: Long, pointType: PointType) {
        TODO("Not yet implemented")
    }

}