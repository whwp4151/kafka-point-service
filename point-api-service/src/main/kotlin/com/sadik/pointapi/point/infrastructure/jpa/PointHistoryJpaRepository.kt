package com.sadik.pointapi.point.infrastructure.jpa

import com.sadik.pointapi.point.application.type.PointType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime

interface PointHistoryJpaRepository: JpaRepository<PointHistoryEntity, Long> {

    @Query(
        """
        SELECT p FROM PointHistoryEntity p
        WHERE p.userId = :userId
        AND p.regDate BETWEEN :begin AND :end
        AND (:types IS NULL OR p.pointType IN :types)
        """
    )
    fun findHistories(
        @Param("userId") userId: Long,
        @Param("types") types: List<PointType>?,
        @Param("begin") begin: LocalDateTime,
        @Param("end") end: LocalDateTime
    ): List<PointHistoryEntity>

    fun findByUserIdOrderByIdDesc(userId: Long): List<PointHistoryEntity>

}