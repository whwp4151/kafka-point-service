package com.sadik.pointapi.point.controller.dto

import com.sadik.pointapi.point.application.dto.PointState
import com.sadik.pointapi.point.application.dto.PointSummaryDto

data class PointEarnResponseDto(
    val pointState: PointState,
    val pointSummary: PointSummaryDto
)