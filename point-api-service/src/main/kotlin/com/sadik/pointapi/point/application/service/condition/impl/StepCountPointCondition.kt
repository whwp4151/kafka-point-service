package com.sadik.pointapi.point.application.service.condition.impl

import com.sadik.pointapi.point.application.dto.PointState
import com.sadik.pointapi.point.application.service.PointService
import com.sadik.pointapi.point.application.service.condition.IPointCondition
import com.sadik.pointapi.point.application.type.PointType
import com.sadik.pointapi.point.infrastructure.jpa.PointHistoryEntity
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class StepCountPointCondition : IPointCondition {

    private val log = LoggerFactory.getLogger(this::class.java)

    private val pointType: PointType = PointType.STEP_COUNTER
    private val MaxStepCount: Int = 40_000

    override fun calcPoint(
        userId: Long,
        pointService: PointService
    ): Long {
        // 발동, 제한 조건을 모두 만족했느냐?
        if (checkExecuteCond() && checkLimitCond()) {
            return pointType.point
        }

        return 0
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

    // 발동(실행) 조건
    private fun checkExecuteCond(): Boolean {
        // 0 ~ 70,000 사이의 임의의 걸음수 생성
        val step = (0..70_000).random()

        // 발동 조건 40000 걸음 이상 걸었나
        if (step < MaxStepCount) {
            log.info("[point] deny:{}, step count: ", step)
            return false
        }

        return true
    }

    // 제한 조건
    private fun checkLimitCond(): Boolean {
        // 제한 조건, 매주 한번
        val now = LocalDate.now()

        // TODO
        val points: List<PointHistoryEntity> = emptyList()
        if (points.size > 0) {
            log.info("[point] deny:{}, already paid. point-uuid:{}", pointType, points.get(0).uuid)
            return false // 이미 발급
        }

        return true
    }
}