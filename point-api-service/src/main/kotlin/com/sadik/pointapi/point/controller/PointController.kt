package com.sadik.pointapi.point.controller

import com.sadik.pointapi.common.dto.ApiResponse
import com.sadik.pointapi.point.application.dto.PointHistoryDto
import com.sadik.pointapi.point.application.usecase.PointUseCase
import com.sadik.pointapi.point.controller.dto.PointEarnRequestDto
import com.sadik.pointapi.point.controller.dto.PointEarnResponseDto
import com.sadik.pointapi.user.application.usecase.UserUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/point")
class PointController(
    private val pointUseCase: PointUseCase,
    private val userUseCase: UserUseCase
) {

    @PostMapping("/earn")
    fun earnPoint(@RequestBody request: PointEarnRequestDto): ResponseEntity<ApiResponse<PointEarnResponseDto>> {
        // userId 확인
        val userDto = userUseCase.findByUserId(request.userId)

        val result = pointUseCase.grantConditionalPoint(userDto.id, request.pointType)
        if (!result) {
            throw RuntimeException("fail to add point")
        }

        val pointState = pointUseCase.getPointState(request.userId, request.pointType)
        val pointSummary = pointUseCase.getPointSummary(request.userId)

        return ResponseEntity.ok(ApiResponse.success(data = PointEarnResponseDto(pointState, pointSummary)))
    }

    @GetMapping("/history/{userId}")
    fun getPointHistory(@PathVariable userId: Long): ResponseEntity<ApiResponse<List<PointHistoryDto>>> {
        val result = pointUseCase.findByUserId(userId)
        return ResponseEntity.ok(ApiResponse.success(result))
    }

}