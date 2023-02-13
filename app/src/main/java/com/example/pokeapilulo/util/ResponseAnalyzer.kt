package com.example.pokeapilulo.util

import retrofit2.Response

abstract class ResponseAnalyzer {

    protected inline fun <reified T> getResponseResult(apiCall: () -> Response<T>): ResponseWrapper<T> {
        var responseCode = 0
        try {
            val response = apiCall()
            responseCode = response.code()
            if(response.isSuccessful){
                val responseBody = response.body()
                if(responseBody != null)
                    return ResponseWrapper.getResponseSuccess(response.body())
            }
            return getError(
                Exception(response.message()),
                responseCode,
                response.errorBody()?.string().orEmpty()
            )

        }catch (exception: Exception){
            return getError(exception, responseCode, "")
        }
    }

    fun <T> getError(exceptionMessage: Exception, responseCode: Int, errorBody: String? = "", responseBody: T? = null): ResponseWrapper<T>{
        return ResponseWrapper.getResponseError(exceptionMessage, responseCode, errorBody, responseBody)
    }
}