package com.merseyside.testsharedproject.yobitapi.net

import io.ktor.client.engine.HttpClientEngine

class YobitApi(private val engine: HttpClientEngine) {

    private val responseCreator = YobitResponseCreator(engine)



}