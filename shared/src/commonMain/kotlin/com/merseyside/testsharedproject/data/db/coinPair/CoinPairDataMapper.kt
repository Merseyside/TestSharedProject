package com.merseyside.testsharedproject.data.db.coinPair

import com.merseyside.testsharedproject.domain.CoinPair
import com.merseyside.testsharedproject.yobitapi.entity.response.CoinPairResponse

class CoinPairDataMapper {

    fun transform(str: String) : CoinPairEntity {
        val splits = str.split(",")

        return CoinPairEntity(splits[0], splits[1].toDouble(), splits[2].toDouble(), splits[3].toDouble())
    }

    fun transformToDBEntity(coinPairResponse: CoinPairResponse) : List<Pair<Long, CoinPairEntity>> {

        val list = ArrayList<Pair<Long, CoinPairEntity>>()

        coinPairResponse.pairs.forEach {
            list.add(
                Pair(
                    it.value.updated,
                    CoinPairEntity(
                        name = it.key,
                        high = it.value.high,
                        low = it.value.low,
                        avg = it.value.average
                    )
            ))
        }
        return list
    }

    fun transformToString(coinPair: CoinPairEntity) : String {
        return "${coinPair.name},${coinPair.high},${coinPair.low},${coinPair.avg}"
    }

    fun transform(coinPairEntity: CoinPairEntity) : CoinPair {
        return CoinPair(
            name = coinPairEntity.name,
            high = coinPairEntity.high,
            low = coinPairEntity.low,
            average = coinPairEntity.avg
        )
    }
}