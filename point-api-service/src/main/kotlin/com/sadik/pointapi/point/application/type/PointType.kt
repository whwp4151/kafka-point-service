package com.sadik.pointapi.point.application.type

enum class PointType(
    val description: String,
    val point: Long
) {
    JOIN("회원가입", 1000),
    QUIZ("오늘의 퀴즈", 50),
    STEP_COUNTER("만보기", 500)
}