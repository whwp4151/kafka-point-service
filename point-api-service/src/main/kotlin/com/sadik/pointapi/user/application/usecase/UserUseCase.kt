package com.sadik.pointapi.user.application.usecase

import com.sadik.pointapi.user.application.dto.UserDto

interface UserUseCase {
    fun join(name: String): Long
    fun findByUserId(userId: Long): UserDto
}