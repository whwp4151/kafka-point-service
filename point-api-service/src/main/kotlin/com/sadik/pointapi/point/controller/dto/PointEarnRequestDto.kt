package com.sadik.pointapi.point.controller.dto

import com.sadik.pointapi.point.application.type.PointType

data class PointEarnRequestDto(
    val userId: Long,
    val pointType: PointType
) {
    init {
        require(pointType == PointType.STEP_COUNTER) {
            "현재는 STEP_COUNTER 포인트 타입만 허용됩니다."
        }
    }
}