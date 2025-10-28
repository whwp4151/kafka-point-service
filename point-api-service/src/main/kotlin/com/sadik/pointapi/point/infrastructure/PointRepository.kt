package com.sadik.pointapi.point.infrastructure

import com.sadik.pointapi.point.application.adapter.PointAdapter
import com.sadik.pointapi.point.application.dto.PointHistoryDto
import com.sadik.pointapi.point.application.dto.command.PointHistoryCommand
import com.sadik.pointapi.point.application.dto.query.PointHistoryQuery
import com.sadik.pointapi.point.infrastructure.jpa.PointHistoryEntity
import com.sadik.pointapi.point.infrastructure.jpa.PointHistoryJpaRepository
import org.springframework.stereotype.Repository
import kotlin.collections.map

@Repository
class PointRepository(
    private val pointHistoryJpaRepository: PointHistoryJpaRepository
) : PointAdapter {

    override fun insertPointHistory(command: PointHistoryCommand): Long {
        val entity = pointHistoryJpaRepository.save(PointHistoryEntity.fromCommand(command));
        return entity.id ?: throw IllegalStateException("Point History ID must not be null after save.")
    }

    override fun getPointHistory(query: PointHistoryQuery): List<PointHistoryDto> {
        val result = pointHistoryJpaRepository.findHistories(
            query.userId,
            query.typeList,
            query.begin,
            query.end
        )

        return result.map { PointHistoryDto.of(it) }
    }

    override fun findByUserId(userId: Long): List<PointHistoryDto> {
        val result = pointHistoryJpaRepository.findByUserIdOrderByIdDesc(userId)
        return result.map { PointHistoryDto.of(it) }
    }

}