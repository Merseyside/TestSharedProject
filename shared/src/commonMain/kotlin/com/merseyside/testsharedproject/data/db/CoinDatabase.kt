package com.merseyside.testsharedproject.data.db

import com.merseyside.testsharedproject.data.db.coinPair.CoinPairDataMapper
import com.merseyside.testsharedproject.data.db.coinPair.CoinPairValuesEntity
import com.merseyside.testsharedproject.db.model.CoinPairModel
import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.db.SqlDriver

val coinPairDataMapper = CoinPairDataMapper()

fun createDatabase(driver: SqlDriver): CoinDatabase {
    val coordinateAdapter = object : ColumnAdapter<CoinPairValuesEntity, String> {

        override fun decode(databaseValue: String): CoinPairValuesEntity {
            return coinPairDataMapper.transform(databaseValue)
        }

        override fun encode(value: CoinPairValuesEntity): String {
            return coinPairDataMapper.transformToString(value)
        }

    }

    return CoinDatabase(
        driver,
        CoinPairModel.Adapter(
            pairAdapter = coordinateAdapter
        )
    )
}