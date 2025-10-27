package com.sadik.pointapi.user.infrastructure.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository: JpaRepository<UserEntity, Long> {
}