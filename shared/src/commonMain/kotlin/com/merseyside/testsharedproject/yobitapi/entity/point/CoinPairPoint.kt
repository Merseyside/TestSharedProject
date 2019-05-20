package com.merseyside.testsharedproject.yobitapi.entity.point

import kotlinx.serialization.SerialName

data class CoinPairPoint(
    @SerialName("high")
    val high: Double,

    @SerialName("low")
    val low: Double,

    @SerialName("avg")
    val average: Double,

    @SerialName("updated")
    val updated: Long
)