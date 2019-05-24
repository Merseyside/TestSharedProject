package com.merseyside.testsharedproject.domain.interactor

import com.merseyside.testsharedproject.di.kodeinCoinPair
import com.merseyside.testsharedproject.domain.base.FlowUseCase
import com.merseyside.testsharedproject.domain.repository.CoinPairRepository
import com.merseyside.testsharedproject.utils.Logger
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import org.kodein.di.erased.instance

@UseExperimental(FlowPreview::class)
class GetMessagesInteractor : FlowUseCase<String, Any>() {

    private val TAG = "GetMessagesInteractor"

    private val repository: CoinPairRepository by kodeinCoinPair.instance()

    override fun executeOnBackground(params: Any?): Flow<String> {
        return repository.observeNewMsg()
            .filter {
                Logger.logMsg(TAG, "filter executed on $coroutineContext")
                it.split(" ").last().toInt() % 2 == 0
            }
    }
}