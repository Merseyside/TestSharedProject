package com.merseyside.testsharedproject.yobitapi.net

import com.merseyside.testsharedproject.di.netModule
import com.merseyside.testsharedproject.utils.Logger
import com.merseyside.testsharedproject.yobitapi.entity.point.CoinPairPoint
import com.merseyside.testsharedproject.yobitapi.entity.response.CoinPairResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.JsonSerializer
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.request
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.content.OutgoingContent
import io.ktor.http.contentType
import io.ktor.http.takeFrom
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import kotlinx.serialization.parseMap
import org.kodein.di.erased.instance

class YobitResponseCreator(private val engine: HttpClientEngine) {

    private val TAG = "YobitResponseCreator"

    val baseUrl: String by netModule.instance("baseUrl")

    class IgnoreOutgoingContentJsonSerializer(private val delegate: JsonSerializer) : JsonSerializer by delegate {
        override fun write(data: Any): OutgoingContent {
            if (data is OutgoingContent) {
                return data
            }
            return delegate.write(data)
        }
    }

    fun JsonSerializer.ignoreOutgoingContent() = IgnoreOutgoingContentJsonSerializer(this)


    private val client by lazy {
        HttpClient(engine) {
//            install(JsonFeature) {
//                serializer = KotlinxSerializer(JSON.nonstrict).apply {
//                    ignoreOutgoingContent()
//                    //register(CoinPairResponse.serializer())
//                }
//            }

            defaultRequest {
                accept(ContentType.Application.Json)
                //contentType(ContentType.Application.Json)
            }

            expectSuccess = true
        }
    }

    @UseExperimental(ImplicitReflectionSerializer::class)
    suspend fun fetchCoinPair(first: String, second: String) : CoinPairResponse {
        Logger.logMsg(TAG, "fetchCoinPair() first = $first second = $second")

        val call = client.request<String> {

            method = HttpMethod.Get
            url.takeFrom("$baseUrl${first}_$second")
        }

        Logger.logMsg(TAG, "$call")

        val json = Json.nonstrict
        val coinPairs = json.parseMap<String, CoinPairPoint>(call)

        val coinPairResponse = CoinPairResponse(coinPairs)

        Logger.logMsg(TAG, "$coinPairs")

        return coinPairResponse
    }
}