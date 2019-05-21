package com.merseyside.testsharedproject.di


import com.merseyside.testsharedproject.yobitapi.net.YobitApi
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.engine.HttpClientEngine
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider
import org.kodein.di.erased.singleton


internal expect fun getPlatformEngine(): HttpClientEngine

expect var sqlDriver: SqlDriver?

//val databaseModule = Kodein.Module("database") {
//
//
//
//    bind<CoinDatabase>() with singleton {
//
//    }
//}

val coinPairModule = Kodein.Module("coinPair") {



    //bind<CoinPairDao>() with singleton {  }
}


val netModule = Kodein.Module("net") {

    bind<HttpClientEngine>() with provider { getPlatformEngine() }

    bind<YobitApi>() with singleton { YobitApi( instance() ) }
}

val kodein = Kodein {
    import(coinPairModule)
}