package com.sadik.pointapi.user.application.service

import com.sadik.pointapi.user.application.adapter.UserAdapter
import com.sadik.pointapi.user.application.usecase.UserUseCase
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userAdapter: UserAdapter
) : UserUseCase {

    @Transactional
    override fun join(name: String): Long {
        return userAdapter.join(name)
    }

}