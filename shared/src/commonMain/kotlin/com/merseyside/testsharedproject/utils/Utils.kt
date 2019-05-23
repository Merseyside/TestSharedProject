package com.merseyside.testsharedproject.utils

import com.soywiz.klock.DateTime

class Utils {

    companion object {
        fun isExpired(timeSeconds: Long, expiredTimeSeconds: Long) : Boolean {
            val now = DateTime.now().unixMillisLong / 1000

            return now - timeSeconds > expiredTimeSeconds
        }

        fun getUnixTimeSeconds() : Long {
            return DateTime.now().unixMillisLong / 1000
        }
    }
}