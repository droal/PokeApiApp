package com.example.pokeapilulo.util

sealed class ResponseStatus<out O> {
    data class Succes<out T>(val data: T) : ResponseStatus<T>()

    data class Error(val error: ErrorData? = null, val message: String) : ResponseStatus<Nothing>()


}

class ErrorData {

}


