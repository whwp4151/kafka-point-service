package com.sadik.pointapi.point.application.adapter

import com.sadik.pointapi.point.application.dto.PointHistoryDto
import com.sadik.pointapi.point.application.dto.command.PointHistoryCommand
import com.sadik.pointapi.point.application.dto.query.PointHistoryQuery

interface PointAdapter {
    fun insertPointHistory(command: PointHistoryCommand): Long
    fun getPointHistory(query: PointHistoryQuery): List<PointHistoryDto>
    fun findByUserId(userId: Long): List<PointHistoryDto>
}