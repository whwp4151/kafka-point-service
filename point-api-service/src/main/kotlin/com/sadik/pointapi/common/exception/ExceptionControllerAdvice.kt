package com.sadik.pointapi.common.exception

import com.sadik.pointapi.common.dto.ApiResponse
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionControllerAdvice {

    private val log = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(
        request: HttpServletRequest,
        e: RuntimeException
    ): ResponseEntity<ApiResponse<*>> {
        log.error("handleRuntimeException occurred", e)

        return ResponseEntity.ok()
            .body(ApiResponse.error(data = null))
    }

}