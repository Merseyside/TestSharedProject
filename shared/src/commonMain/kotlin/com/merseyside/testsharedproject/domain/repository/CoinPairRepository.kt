package com.merseyside.testsharedproject.domain.repository

import com.merseyside.testsharedproject.domain.CoinPair
import kotlinx.coroutines.flow.Flow

interface CoinPairRepository {

    suspend fun getCoinPair(first: String, second: String) : CoinPair

    fun observeNewMsg(): Flow<String>
}