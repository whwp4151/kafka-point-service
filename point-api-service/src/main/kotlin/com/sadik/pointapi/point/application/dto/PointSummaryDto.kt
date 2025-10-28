package com.sadik.pointapi.point.application.dto

data class PointSummaryDto(
    val total: Long,
    val used: Long,
    val rem: Long,
    val todaySavingPoint: Long
)