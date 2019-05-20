package com.merseyside.testsharedproject.data.db.coinPair

class CoinPairDataMapper {

    fun transform(str: String) : CoinPairEntity {
        val splits = str.split(",")

        return CoinPairEntity(splits[0], splits[1].toFloat(), splits[2].toFloat(), splits[3].toFloat())
    }

    fun transform(coinPair: CoinPairEntity) : String {
        return "${coinPair.name},${coinPair.high},${coinPair.low},${coinPair.avg}"
    }
}