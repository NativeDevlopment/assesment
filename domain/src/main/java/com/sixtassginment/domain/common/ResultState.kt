package com.sixtassginment.domain.common

import com.sixtassginment.domain.entity.ErrorEntity


data class ResultState<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): ResultState<T> {
            return ResultState(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): ResultState<T> {
            return ResultState(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): ResultState<T> {
            return ResultState(Status.LOADING, data, null)
        }

    }

}

enum class Status {
    SUCCESS, ERROR, LOADING
}