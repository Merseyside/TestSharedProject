package com.merseyside.testsharedproject.data.db.coinPair

import com.merseyside.testsharedproject.data.db.CoinDatabase
import com.merseyside.testsharedproject.db.model.CoinPairModel

class CoinPairDao(database: CoinDatabase) {

    private val db = database.coinPairModelQueries

    internal fun insert(
        coinPairModel: CoinPairModel
    ) {
        db.insertItem(
            coinPairModel.name,
            coinPairModel.pair,
            coinPairModel.updateTime
        )
    }

    internal fun select() : List<CoinPairModel> = db.selectAll().executeAsList()

    internal fun selectByPair(name: String) : CoinPairModel? = db.selectByName(name).executeAsOneOrNull()
}