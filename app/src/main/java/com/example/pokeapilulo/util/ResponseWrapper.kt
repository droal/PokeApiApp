package com.example.pokeapilulo.util

data class ResponseWrapper<T>(var responseStatus: ResponseStatus, var responseData: T? = null, val errorMessage: String? = "") {

    sealed class ResponseStatus {
        object SUCCESS : ResponseStatus()
        class ERROR(val throwable: Throwable?, val errorCode: Int, val errorBody: String? = ""): ResponseStatus()
    }

    companion object {
        fun <T> getResponseSuccess(responseData: T?): ResponseWrapper<T> {
            return ResponseWrapper(
                ResponseStatus.SUCCESS,
                responseData,
                null
            )
        }

        fun <T> getResponseError(exception: Exception, errorCode: Int, errorBody: String? = "", responseData: T? = null): ResponseWrapper<T> {
            return ResponseWrapper(
                ResponseStatus.ERROR(exception, errorCode, errorBody),
                responseData,
                exception.message
            )
        }
    }
}
