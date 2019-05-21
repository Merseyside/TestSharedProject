package com.merseyside.testsharedproject.data.repository

import com.merseyside.testsharedproject.data.db.coinPair.CoinPairDao
import com.merseyside.testsharedproject.data.db.coinPair.CoinPairDataMapper
import com.merseyside.testsharedproject.domain.CoinPair
import com.merseyside.testsharedproject.domain.repository.CoinPairRepository
import com.merseyside.testsharedproject.yobitapi.net.YobitApi

class CoinPairRepositoryImpl(
    private val api: YobitApi,
    private val coinPairDao: CoinPairDao,
    private val coinPairMapper: CoinPairDataMapper
) : CoinPairRepository {

    override suspend fun getCoinPair(first: String, second: String) : CoinPair {
        val coinPairResponse = api.fetchCoinPair(first, second)
        val pairList = coinPairMapper.transformToDBEntity(coinPairResponse)

        pairList.forEach {
            coinPairDao.insert(it.second, it.first)
        }

        return coinPairMapper.transform(pairList.first().second)
    }
}