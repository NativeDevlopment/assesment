package com.sixtassignment.util

object Constants {
    val baseURL: String
        get() {
            return "https://cdn.sixt.io/"
        }
    const val CONNECT_TIMEOUT: Long = 60
    const val READ_TIMEOUT: Long = 60
    const val CALL_TIMEOUT: Long = 60
}