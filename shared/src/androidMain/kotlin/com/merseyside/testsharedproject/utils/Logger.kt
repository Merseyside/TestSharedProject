package com.merseyside.testsharedproject.utils

import android.util.Log

internal actual class Logger {

    actual companion object {
        internal actual fun logMsg(tag: String, msg: String) {
            Log.d(tag, msg)
        }

        internal actual fun logError(tag: String, errorMsg: String) {
            Log.e(tag, errorMsg)
        }

    }

}