package com.merseyside.testsharedproject.data.repository

import com.merseyside.testsharedproject.data.db.coinPair.CoinPairDao
import com.merseyside.testsharedproject.data.db.coinPair.CoinPairDataMapper
import com.merseyside.testsharedproject.domain.CoinPair
import com.merseyside.testsharedproject.domain.repository.CoinPairRepository
import com.merseyside.testsharedproject.utils.Logger
import com.merseyside.testsharedproject.utils.Utils
import com.merseyside.testsharedproject.yobitapi.net.YobitApi
import com.soywiz.klock.DateTime
import com.soywiz.klock.seconds

class CoinPairRepositoryImpl(
    private val api: YobitApi,
    private val coinPairDao: CoinPairDao
) : CoinPairRepository {

    private val TAG = "CoinPairRepository"

    private val EXPIRED_TIME = 30.seconds.seconds.toLong()

    private val coinPairMapper by lazy { CoinPairDataMapper() }

    override suspend fun getCoinPair(first: String, second: String) : CoinPair {

        val dbEntity = coinPairDao.selectByPair("${first}_$second")

        return if (dbEntity == null || Utils.isExpired(dbEntity.updateTime, EXPIRED_TIME)) {

            Logger.logMsg(TAG, "null or expired")

            val coinPairResponse = api.fetchCoinPair(first, second)
            val pairList = coinPairMapper.transformToDBEntity(coinPairResponse)

            pairList.forEach {
                coinPairDao.insert(it)
            }

            coinPairMapper.transform(pairList.first())
        } else {
            Logger.logMsg(TAG, "not expired")

            coinPairMapper.transform(dbEntity)
        }
    }
}