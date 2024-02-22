package me.androidbox.utils

inline fun <T> safeApiRequest(block: () -> T): APIResponse<T> {
    return try {
        APIResponse.Success(data = block())
    }
    catch(exception: Exception) {
        APIResponse.Failure(error = exception)
    }
}