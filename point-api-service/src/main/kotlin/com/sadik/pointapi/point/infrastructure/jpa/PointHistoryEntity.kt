package com.sadik.pointapi.point.infrastructure.jpa

import com.sadik.pointapi.common.jpa.BaseEntity
import com.sadik.pointapi.point.application.type.PointActionType
import com.sadik.pointapi.point.application.type.PointType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime
import java.util.UUID

@Entity
class PointHistoryEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val uuid: UUID,

    val userId: Long,

    val point: Long,

    @Enumerated(EnumType.STRING)
    val pointType: PointType,

    @Enumerated(EnumType.STRING)
    val actionType: PointActionType,

    val bigo: String? = null,

    val regDate: LocalDateTime = LocalDateTime.now(),

    val processedAt: LocalDateTime? = null // 외부 시스템 연동 시각

) : BaseEntity()
