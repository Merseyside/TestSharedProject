package com.merseyside.testsharedproject.data.db.coinPair

import com.merseyside.testsharedproject.db.model.CoinPairModel
import com.merseyside.testsharedproject.domain.CoinPair
import com.merseyside.testsharedproject.utils.Utils
import com.merseyside.testsharedproject.yobitapi.entity.response.CoinPairResponse

class CoinPairDataMapper {

    fun transform(str: String) : CoinPairValuesEntity {
        val splits = str.split(",")

        return CoinPairValuesEntity(splits[0].toDouble(), splits[1].toDouble(), splits[2].toDouble())
    }

    fun transformToDBEntity(coinPairResponse: CoinPairResponse) : List<CoinPairModel> {

        val list = ArrayList<CoinPairModel>()

        coinPairResponse.pairs.forEach { responsePair ->
            list.add(
                object : CoinPairModel {
                    override val name: String
                        get() = responsePair.key
                    override val pair: CoinPairValuesEntity?
                        get() = CoinPairValuesEntity(
                            high = responsePair.value.high,
                            low = responsePair.value.low,
                            avg = responsePair.value.average
                        )
                    override val updateTime: Long
                        get() = Utils.getUnixTimeSeconds()
                }
            )
        }

        return list
    }

    fun transformToString(coinPair: CoinPairValuesEntity) : String {
        return "${coinPair.high},${coinPair.low},${coinPair.avg}"
    }

    fun transform(coinPairEntity: CoinPairModel) : CoinPair {
        return CoinPair(
            name = coinPairEntity.name,
            high = coinPairEntity.pair?.high ?: 0.0,
            low = coinPairEntity.pair?.low  ?: 0.0,
            average = coinPairEntity.pair?.avg ?: 0.0
        )
    }
}