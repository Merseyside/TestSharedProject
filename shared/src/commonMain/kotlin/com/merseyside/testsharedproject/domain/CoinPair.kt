package com.merseyside.testsharedproject.domain

import kotlinx.serialization.Serializable

@Serializable
data class CoinPair(
    val name: String,
    val high: Double,
    val low: Double,
    val average: Double)