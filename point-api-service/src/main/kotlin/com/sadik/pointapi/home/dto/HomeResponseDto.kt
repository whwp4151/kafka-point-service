package com.sadik.pointapi.home.dto

import com.sadik.pointapi.point.application.dto.PointState
import com.sadik.pointapi.point.application.dto.PointSummaryDto

data class HomeResponseDto(
    val pointStateList: List<PointState>,
    val pointSummary: PointSummaryDto
)