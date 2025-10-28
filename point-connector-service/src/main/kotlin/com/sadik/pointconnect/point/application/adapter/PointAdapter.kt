package com.sadik.pointconnect.point.application.adapter

import java.time.LocalDateTime
import java.util.UUID

interface PointAdapter {
    fun processPoint(uuid: UUID, processdAt: LocalDateTime)
}