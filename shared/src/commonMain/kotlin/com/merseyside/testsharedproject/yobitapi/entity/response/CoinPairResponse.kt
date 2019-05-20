package com.merseyside.testsharedproject.yobitapi.entity.response

import com.merseyside.testsharedproject.yobitapi.entity.point.CoinPairPoint
import kotlinx.serialization.SerialName

data class CoinPairResponse(

    val pairs: Map<String, CoinPairPoint>
)