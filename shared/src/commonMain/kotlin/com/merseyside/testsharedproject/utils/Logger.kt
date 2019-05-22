package com.merseyside.testsharedproject.utils

internal expect class Logger {

    companion object {

        internal fun logMsg(tag: String, msg: String)

        internal fun logError(tag: String, errorMsg: String)
    }
}