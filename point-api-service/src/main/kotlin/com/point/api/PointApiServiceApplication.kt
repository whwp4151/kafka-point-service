package com.point.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PointApiServiceApplication

fun main(args: Array<String>) {
    runApplication<PointApiServiceApplication>(*args)
}
