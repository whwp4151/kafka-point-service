package com.sadik.pointapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/health")
class HealthCheckController {

    @GetMapping
    fun health(): Map<String, String> {
        return mapOf("status" to "UP")
    }
}