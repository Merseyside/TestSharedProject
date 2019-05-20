package com.merseyside.testsharedproject.data.db.coinPair

import kotlinx.serialization.Serializable

@Serializable
data class CoinPairEntity(
    val name: String,
    val high: Float,
    val low: Float,
    val avg: Float
)