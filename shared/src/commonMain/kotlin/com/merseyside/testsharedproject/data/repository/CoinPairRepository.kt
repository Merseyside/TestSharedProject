package com.merseyside.testsharedproject.data.repository

import com.merseyside.testsharedproject.data.db.coinPair.CoinPairEntity

class CoinPairRepository(

) {

    suspend fun getCoinPair(first: String, second: String): CoinPairEntity? {
        return null
    }
}