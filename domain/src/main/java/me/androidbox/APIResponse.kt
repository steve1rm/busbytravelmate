package me.androidbox

import java.lang.Exception

sealed interface APIResponse<out T> {
    data object IsLoading : APIResponse<Nothing>
    data class Success<T>(val data: T) : APIResponse<T>
    data class Failure(val error: Exception) : APIResponse<Nothing>

    suspend fun onSuccess(block: suspend (data: T) -> Unit): APIResponse<T> {
        if (this is Success) {
            block(data)
        }
        return this
    }

    suspend fun onFailure(block: suspend (error: Exception) -> Unit): APIResponse<T> {
        if (this is Failure) {
            block(error)
        }
        return this
    }
}
