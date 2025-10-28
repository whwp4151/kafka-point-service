package com.sadik.pointapi.point.application.dto.command

import com.sadik.pointapi.point.application.type.PointActionType
import com.sadik.pointapi.point.application.type.PointType
import java.util.UUID

data class PointHistoryCommand(
    val uuid: UUID,
    val userId: Long,
    val point: Long,
    val pointType: PointType,
    val actionType: PointActionType,
    val bigo: String? = null
) {

    companion object{
        fun of(
            uuid: UUID,
            userId: Long,
            point: Long,
            pointType: PointType,
            actionType: PointActionType,
            bigo: String? = null
        ): PointHistoryCommand {
            return PointHistoryCommand(
                uuid = uuid,
                userId = userId,
                point = point,
                pointType = pointType,
                actionType = actionType,
                bigo = bigo
            )
        }
    }

}