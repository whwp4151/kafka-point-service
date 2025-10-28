package com.sadik.pointconnect.point.application.dto

import com.sadik.pointconnect.point.infrastructure.jpa.PointHistoryEntity
import com.sadik.pointconnect.point.application.type.PointActionType
import com.sadik.pointconnect.point.application.type.PointType
import java.time.LocalDateTime
import java.util.UUID

data class PointHistoryDto(
    val id: Long,
    val uuid: UUID,
    val userId: Long,
    val point: Long,
    val pointType: PointType,
    val actionType: PointActionType,
    val bigo: String? = null,
    val regDate: LocalDateTime,
    val processedAt: LocalDateTime? = null
) {
    
    companion object {
        fun of(entity: PointHistoryEntity): PointHistoryDto {
            return PointHistoryDto(
                id = entity.id!!,
                uuid = entity.uuid,
                userId = entity.userId,
                point = entity.point,
                pointType = entity.pointType,
                actionType = entity.actionType,
                bigo = entity.bigo,
                regDate = entity.regDate,
                processedAt = entity.processedAt
            )
        }
    }
    
}