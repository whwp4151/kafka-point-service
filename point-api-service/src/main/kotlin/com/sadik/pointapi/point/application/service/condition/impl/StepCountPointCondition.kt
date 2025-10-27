package com.sadik.pointapi.point.application.service.condition.impl

import com.sadik.pointapi.point.application.dto.PointState
import com.sadik.pointapi.point.application.service.PointService
import com.sadik.pointapi.point.application.service.condition.IPointCondition
import com.sadik.pointapi.point.application.type.PointType
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class StepCountPointCondition : IPointCondition {

    private val log = LoggerFactory.getLogger(this::class.java)

    private val pointType: PointType = PointType.STEP_COUNTER

    override fun calcPoint(
        userId: Long,
        pointService: PointService
    ): Long {
        TODO("Not yet implemented")
    }

    override fun getPointType(): PointType {
        return pointType
    }

    override fun getPointState(
        userId: Long,
        pointService: PointService
    ): PointState {
        TODO("Not yet implemented")
    }
}