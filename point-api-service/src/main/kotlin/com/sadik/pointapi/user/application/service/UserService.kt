package com.sadik.pointapi.user.application.service

import com.sadik.pointapi.user.application.adapter.UserAdapter
import com.sadik.pointapi.user.application.dto.UserDto
import com.sadik.pointapi.user.application.usecase.UserUseCase
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userAdapter: UserAdapter
) : UserUseCase {

    @Transactional
    override fun join(name: String): Long {
        return userAdapter.join(name)
    }

    @Transactional(readOnly = true)
    override fun findByUserId(userId: Long): UserDto {
        return userAdapter.findByUserId(userId)
    }

}