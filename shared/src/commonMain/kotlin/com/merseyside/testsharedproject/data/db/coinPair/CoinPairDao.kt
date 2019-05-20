package com.merseyside.testsharedproject.data.db.coinPair

import com.merseyside.testsharedproject.data.db.CoinDatabase
import com.merseyside.testsharedproject.db.model.CoinPairModel

class CoinPairDao(database: CoinDatabase) {

    private val db = database.coinPairModelQueries

    internal fun insert(item: CoinPairEntity, time: Long) {
        db.insertItem(
            item,
            time
        )
    }

    internal fun select() : List<CoinPairModel> = db.selectAll().executeAsList()
}