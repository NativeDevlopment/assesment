package com.sixtassginment.domain.entity

sealed class ErrorEntity {

    data class Error(val errorCode: Any?, val errorMessage: String? = "") : ErrorEntity()
}