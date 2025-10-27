package com.sadik.pointapi.point.controller

import com.sadik.pointapi.common.dto.ApiResponse
import com.sadik.pointapi.point.application.usecase.PointUseCase
import com.sadik.pointapi.point.controller.dto.PointEarnRequestDto
import com.sadik.pointapi.user.application.usecase.UserUseCase
import org.springframework.http.ResponseEntity
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
    fun earnPoint(@RequestBody request: PointEarnRequestDto): ResponseEntity<ApiResponse<Boolean>> {
        // userId 확인
        val userDto = userUseCase.findByUserId(request.userId)

        val result = pointUseCase.addPoint(userDto.id, request.pointType)

        // TODO
        return ResponseEntity.ok(ApiResponse.success(data = true))
    }

}