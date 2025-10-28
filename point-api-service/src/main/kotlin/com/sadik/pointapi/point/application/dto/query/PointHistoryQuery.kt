package com.sadik.pointapi.point.application.dto.query

import com.sadik.pointapi.point.application.type.PointType
import java.time.LocalDateTime

data class PointHistoryQuery(
    val userId: Long,
    val typeList: List<PointType>?,
    val begin: LocalDateTime,
    val end: LocalDateTime
)