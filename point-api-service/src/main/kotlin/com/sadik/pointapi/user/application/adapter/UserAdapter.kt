package com.sadik.pointapi.user.application.adapter

import com.sadik.pointapi.user.application.dto.UserDto

interface UserAdapter {
    fun join(name: String): Long
    fun findByUserId(userId: Long): UserDto
}