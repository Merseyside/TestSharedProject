package com.merseyside.testsharedproject.domain

import kotlinx.serialization.Serializable

@Serializable
data class CoinPair(
    val name: String,
    val high: Float,
    val low: Float,
    val avg: Float)