package com.sadik.pointapi.user.controller

import com.sadik.pointapi.common.dto.ApiResponse
import com.sadik.pointapi.user.application.usecase.UserUseCase
import com.sadik.pointapi.user.controller.dto.UserJoinRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userUseCase: UserUseCase
) {

    @PostMapping("/join")
    fun join(@RequestBody request: UserJoinRequestDto): ResponseEntity<ApiResponse<Long>> {
        val result = userUseCase.join(request.name)
        return ResponseEntity.ok(ApiResponse.success(data = result))
    }

}