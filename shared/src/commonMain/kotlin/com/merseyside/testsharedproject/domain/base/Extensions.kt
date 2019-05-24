package com.merseyside.testsharedproject.domain.base

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.asFlow

@FlowPreview
fun <T> T.asFlow() : Flow<T> = listOf(this).asFlow()