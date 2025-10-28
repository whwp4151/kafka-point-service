package com.sadik.pointapi.point.application.service

import com.github.f4b6a3.uuid.UuidCreator
import com.sadik.pointapi.point.application.adapter.PointAdapter
import com.sadik.pointapi.point.application.dto.PointHistoryDto
import com.sadik.pointapi.point.application.dto.command.PointHistoryCommand
import com.sadik.pointapi.point.application.dto.query.PointHistoryQuery
import com.sadik.pointapi.point.application.service.condition.IPointCondition
import com.sadik.pointapi.point.application.type.PointActionType
import com.sadik.pointapi.point.application.type.PointType
import com.sadik.pointapi.point.application.usecase.PointUseCase
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class PointService(
    private val pointAdapter: PointAdapter,
    private val condList: List<IPointCondition>
) : PointUseCase {

    private val log = LoggerFactory.getLogger(this::class.java)

    private lateinit var condMap: Map<PointType, IPointCondition>

    @PostConstruct
    fun init() {
        condMap = condList.associateBy { it.getPointType() }
    }

    @Transactional
    override fun addPoint(userId: Long, pointType: PointType): Boolean {
        val point = calcPoint(userId, pointType)
        if (point <= 0) {
            log.info("[point] - deny:{} userId:{}", pointType, userId)
            return false
        }

        // TODO syncPointApi

        // 히스토리 저장
        val uuid = UuidCreator.getTimeOrderedEpoch()
        val command = PointHistoryCommand.of(
            uuid,
            userId,
            point,
            pointType,
            PointActionType.EARN,
            pointType.description
        )
        val pointNo = pointAdapter.insertPointHistory(command)

        log.info("[point] + add:$pointType, point:$point, userId:$userId, point-PK:$pointNo");
        return true
    }

    private fun calcPoint(userId: Long, pointType: PointType): Long {
        val cond = condMap[pointType]
            ?: throw IllegalArgumentException("Point condition not found for type: $pointType")
        return cond.calcPoint(userId, this)
    }

    @Transactional(readOnly = true)
    override fun getPointHistory(
        userId: Long,
        pointType: PointType,
        begin: LocalDate,
        now: LocalDate
    ): List<PointHistoryDto> {
        val beginDateTime = begin.atStartOfDay()
        val endDateTime = now.atTime(23, 59, 59)

        val query = PointHistoryQuery(userId, listOf(pointType), beginDateTime, endDateTime)
        return pointAdapter.getPointHistory(query)
    }

}