package com.sadik.pointapi.user.application.usecase

interface UserUseCase {
    fun join(name: String): Long
}