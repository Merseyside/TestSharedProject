package com.merseyside.testsharedproject.yobitapi.entity.point

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinPairPoint(
    @SerialName("high")
    val high: Double,

    @SerialName("low")
    val low: Double,

    @SerialName("average")
    val average: Double,

    @SerialName("updated")
    val updated: Long
)