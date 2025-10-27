package com.sadik.pointapi.point.controller

import com.sadik.pointapi.common.dto.ApiResponse
import com.sadik.pointapi.point.application.usecase.PointUseCase
import com.sadik.pointapi.point.controller.dto.PointEarnRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/point")
class PointController(
    private val pointUseCase: PointUseCase
) {

    @PostMapping("/earn")
    fun earnPoint(@RequestBody request: PointEarnRequestDto): ResponseEntity<ApiResponse<Boolean>> {
        val result = pointUseCase.addPoint(request.userId, request.pointType)

        // TODO
        return ResponseEntity.ok(ApiResponse.success(data = true))
    }

}