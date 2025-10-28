package com.sadik.pointapi.point.application.dto

import com.sadik.pointapi.point.application.type.PointType
import java.util.UUID

data class PointEarnedEvent(
    val uuid: UUID,
    val userId: Long,
    val point: Long,
    val pointType: PointType
)