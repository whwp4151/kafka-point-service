package com.sadik.pointapi.point.application.service

import com.github.f4b6a3.uuid.UuidCreator
import com.sadik.pointapi.point.application.adapter.PointAdapter
import com.sadik.pointapi.point.application.service.condition.IPointCondition
import com.sadik.pointapi.point.application.type.PointType
import com.sadik.pointapi.point.application.usecase.PointUseCase
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

        val uuid = UuidCreator.getTimeOrderedEpoch()

        return true
    }

    private fun calcPoint(userId: Long, pointType: PointType): Long {
        val cond = condMap[pointType]
            ?: throw IllegalArgumentException("Point condition not found for type: $pointType")
        return cond.calcPoint(userId, this)
    }

}