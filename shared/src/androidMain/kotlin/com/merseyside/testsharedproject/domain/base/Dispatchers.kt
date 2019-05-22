package com.merseyside.testsharedproject.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual val networkContext: CoroutineDispatcher
    get() = Dispatchers.IO

internal actual val applicationContext: CoroutineDispatcher
    get() = Dispatchers.Default