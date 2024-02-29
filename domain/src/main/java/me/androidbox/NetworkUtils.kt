package me.androidbox

import java.util.concurrent.CancellationException

inline fun <T> safeApiRequest(block: () -> T): APIResponse<T> {
    return try {
        APIResponse.Success(data = block())
    }
    catch(exception: Exception) {
        if(exception is CancellationException) {
            throw exception
        }
        else {
            APIResponse.Failure(error = exception)
        }
    }
}