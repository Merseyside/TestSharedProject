package com.merseyside.testsharedproject.data.db.coinPair

import kotlinx.serialization.Serializable

@Serializable
data class CoinPairValuesEntity(
    val high: Double,
    val low: Double,
    val avg: Double
)