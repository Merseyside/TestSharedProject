package com.merseyside.testsharedproject.domain.repository

import com.merseyside.testsharedproject.domain.CoinPair

interface CoinPairRepository {

    suspend fun getCoinPair(first: String, second: String) : CoinPair
}