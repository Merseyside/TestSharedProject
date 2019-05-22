package com.merseyside.testsharedproject.di


import com.merseyside.testsharedproject.data.db.CoinDatabase
import com.merseyside.testsharedproject.data.db.coinPair.CoinPairDao
import com.merseyside.testsharedproject.data.db.createDatabase
import com.merseyside.testsharedproject.data.repository.CoinPairRepositoryImpl
import com.merseyside.testsharedproject.domain.repository.CoinPairRepository
import com.merseyside.testsharedproject.utils.Logger
import com.merseyside.testsharedproject.yobitapi.net.YobitApi
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.engine.HttpClientEngine
import io.ktor.util.InternalAPI
import org.kodein.di.Kodein
import org.kodein.di.erased.*

internal expect fun getPlatformEngine(): HttpClientEngine

expect var sqlDriver: SqlDriver?

val databaseModule = Kodein.Module("database") {

    bind<CoinDatabase>() with singleton {
        createDatabase(sqlDriver!!)
    }
}

val coinPairModule = Kodein.Module("coinPair") {

    bind<CoinPairDao>() with singleton { CoinPairDao( instance()) }

    bind<CoinPairRepository>() with singleton {
        Logger.logMsg("kodein", "here")
        CoinPairRepositoryImpl( instance(), instance() ) }
}

val netModule = Kodein {

    bind<HttpClientEngine>() with provider { getPlatformEngine() }

    bind<YobitApi>() with singleton { YobitApi( instance() ) }

    constant("baseUrl") with "https://yobitex.net/api/3/ticker/"
}

val kodeinCoinPair = Kodein {
    extend(netModule)
    import(databaseModule)
    import(coinPairModule)
}