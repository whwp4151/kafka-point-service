package com.sadik.pointapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class PointApiServiceApplication

fun main(args: Array<String>) {
    // 애플리케이션 시작 전에 기본 타임존을 KST로 설정
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))

    runApplication<PointApiServiceApplication>(*args)
}
