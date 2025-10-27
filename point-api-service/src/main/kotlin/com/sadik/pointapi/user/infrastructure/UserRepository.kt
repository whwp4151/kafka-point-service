package com.sadik.pointapi.user.infrastructure

import com.sadik.pointapi.user.application.adapter.UserAdapter
import com.sadik.pointapi.user.infrastructure.jpa.UserEntity
import com.sadik.pointapi.user.infrastructure.jpa.UserJpaRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val userJpaRepository: UserJpaRepository
) : UserAdapter {
    override fun join(name: String): Long {
        val user = userJpaRepository.save(UserEntity(name = name))
        return user.id ?: throw IllegalStateException("User ID must not be null after save.")
    }
}