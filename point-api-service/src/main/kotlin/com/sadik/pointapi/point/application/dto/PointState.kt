package com.sadik.pointapi.point.application.dto

import com.sadik.pointapi.point.application.type.PointType

data class PointState(
    val point: Long = 0, // 적립 포인트
    val pointType: PointType, // 포인트 타입
    val state: State, // 상태
    val optionalValue: String? = null // 선택적인 상태 값.
){
    enum class State {
        None,                   // 미진행.
        ConditionCompleted,     // 포인트 지급 조건 달성
        PointPaid               // 포인트 지급한 상태.
    }
}