package com.merseyside.testsharedproject.yobitapi.net

import com.merseyside.testsharedproject.yobitapi.entity.response.CoinPairResponse
import io.ktor.client.engine.HttpClientEngine
import io.ktor.util.InternalAPI

class YobitApi(private val engine: HttpClientEngine) {

    private val responseCreator = YobitResponseCreator(engine)

    suspend fun fetchCoinPair(first: String, second: String) : CoinPairResponse {
        return responseCreator.fetchCoinPair(first, second)
    }

}