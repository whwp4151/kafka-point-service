package com.sadik.pointapi.home

import com.sadik.pointapi.common.dto.ApiResponse
import com.sadik.pointapi.home.dto.HomeResponseDto
import com.sadik.pointapi.point.application.dto.PointState
import com.sadik.pointapi.point.application.type.PointType
import com.sadik.pointapi.point.application.usecase.PointUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/home")
class HomeController(
    private val pointUseCase: PointUseCase
) {

    @GetMapping("/{userId}")
    fun getHomeInfo(@PathVariable userId: Long): ResponseEntity<ApiResponse<HomeResponseDto>> {
        val pointStateList = getPointState(userId)

        val pointSummary = pointUseCase.getPointSummary(userId)
        return ResponseEntity.ok(ApiResponse.success(HomeResponseDto(pointStateList, pointSummary)))
    }

    private fun getPointState(userId: Long): List<PointState> {
        val pointTypes = listOf(
            PointType.STEP_COUNTER,
        )

        return pointTypes.map { pointUseCase.getPointState(userId, it) }
    }

}