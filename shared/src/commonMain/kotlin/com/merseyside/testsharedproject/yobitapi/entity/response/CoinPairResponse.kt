package com.merseyside.testsharedproject.yobitapi.entity.response

import com.merseyside.testsharedproject.yobitapi.entity.point.CoinPairPoint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinPairResponse(

    val pairs: Map<String, CoinPairPoint>
)