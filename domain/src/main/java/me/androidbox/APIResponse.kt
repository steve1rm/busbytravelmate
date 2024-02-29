package me.androidbox

import java.lang.Exception

sealed interface APIResponse<T>  {
    data class Success<T>(val data: T) : APIResponse<T>
    data class Failure<Nothing>(val error: Exception) : APIResponse<Nothing>

    fun onSuccess(block: (data: T) -> Unit): APIResponse<T> {
        if (this is Success) {
            block(data)
        }
        return this
    }

    fun onFailure(block: (error: Exception) -> Unit): APIResponse<T> {
        if (this is Failure) {
            block(error)
        }
        return this
    }
}

