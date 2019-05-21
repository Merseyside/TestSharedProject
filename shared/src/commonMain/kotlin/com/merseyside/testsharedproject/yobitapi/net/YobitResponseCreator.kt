package com.merseyside.testsharedproject.yobitapi.net

import com.merseyside.testsharedproject.yobitapi.entity.response.CoinPairResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.json.JSON

class YobitResponseCreator(private val engine: HttpClientEngine) {

    private val client by lazy {
        HttpClient(engine) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(JSON.nonstrict)
            }
        }
    }

    suspend fun fetchCoinPair(first: String, second: String) : CoinPairResponse {
        return client.get {
            url {
                "https://yobitex.net/api/3/ticker/${first}_$second"
            }
        }
    }
}