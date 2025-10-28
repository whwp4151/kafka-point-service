package com.sadik.pointconnect.point.application.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.sadik.pointconnect.point.application.type.PointType
import java.util.UUID

data class PointEarnedEvent @JsonCreator constructor(
    @JsonProperty("uuid") val uuid: UUID,
    @JsonProperty("userId") val userId: Long,
    @JsonProperty("point") val point: Long,
    @JsonProperty("pointType") val pointType: PointType
)