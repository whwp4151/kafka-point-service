package com.sadik.pointapi.common.dto

data class ApiResponse<T>(
    val code: Int,
    val message: String,
    val data: T? = null
) {
    companion object {
        fun <T> success(data: T? = null, message: String = "SUCCESS"): ApiResponse<T> =
            ApiResponse(code = 200, message = message, data = data)

        fun <T> error(code: Int = 500, message: String = "ERROR", data: T? = null): ApiResponse<T> =
            ApiResponse(code = code, message = message, data = data)
    }
}