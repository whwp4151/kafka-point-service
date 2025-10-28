package com.sadik.pointapi.point.application.adapter

import com.sadik.pointapi.point.application.dto.PointEarnedEvent

interface EventPublisher {
    fun addPoint(event: PointEarnedEvent)
}