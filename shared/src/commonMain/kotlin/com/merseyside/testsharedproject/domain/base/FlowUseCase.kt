package com.merseyside.testsharedproject.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.flowWith
import kotlin.coroutines.CoroutineContext

@FlowPreview
abstract class FlowUseCase<T, Params> : CoroutineScope by CoroutineScope(Dispatchers.Main) {

    var backgroundContext: CoroutineContext = applicationContext

    protected abstract fun executeOnBackground(params: Params?): Flow<T>

    fun observe(params: Params?): Flow<T> {

        return executeOnBackground(params)
                .flowOn(backgroundContext)

    }
}