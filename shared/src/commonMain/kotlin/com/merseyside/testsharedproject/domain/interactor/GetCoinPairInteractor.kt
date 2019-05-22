package com.merseyside.testsharedproject.domain.interactor

import com.merseyside.testsharedproject.di.kodeinCoinPair
import com.merseyside.testsharedproject.domain.CoinPair
import com.merseyside.testsharedproject.domain.base.CoroutineUseCase
import com.merseyside.testsharedproject.domain.repository.CoinPairRepository
import org.kodein.di.erased.instance

class GetCoinPairInteractor : CoroutineUseCase<CoinPair, GetCoinPairInteractor.Params>() {

    private val repository: CoinPairRepository by kodeinCoinPair.instance()

    override suspend fun executeOnBackground(params: Params?): CoinPair {
        return repository.getCoinPair(params!!.first, params.second)
    }

    data class Params(val first: String, val second: String)
}