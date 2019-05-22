package com.merseyside.testsharedproject.yobitapi.net

import com.merseyside.testsharedproject.di.netModule
import com.merseyside.testsharedproject.utils.Logger
import com.merseyside.testsharedproject.yobitapi.entity.response.CoinPairResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.JsonSerializer
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.takeFrom
import kotlinx.serialization.json.JSON
import org.kodein.di.erased.instance

class YobitResponseCreator(private val engine: HttpClientEngine) {

    private val TAG = "YobitResponseCreator"

    private val client by lazy {
        HttpClient(engine) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(JSON.nonstrict)
            }
        }
    }

    suspend fun fetchCoinPair(first: String, second: String) : CoinPairResponse {
        Logger.logMsg(TAG, "fetchCoinPair() first = $first second = $second")

        val baseUrl: String by netModule.instance("baseUrl")

        val call = client.request<CoinPairResponse> {
            method = HttpMethod.Get
            url.takeFrom("$baseUrl${first}_$second")
        }

        Logger.logMsg(TAG, "$call")

        return call
    }
}