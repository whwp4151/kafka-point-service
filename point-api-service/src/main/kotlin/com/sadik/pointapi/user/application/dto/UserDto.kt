package com.sadik.pointapi.user.application.dto

import com.sadik.pointapi.user.infrastructure.jpa.UserEntity

data class UserDto(
    val id: Long,
    val name: String
) {
    companion object {
        fun of(entity: UserEntity): UserDto {
            return UserDto(
                id = entity.id!!,
                name = entity.name
            )
        }
    }
}