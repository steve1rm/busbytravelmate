package me.androidbox.domain

inline fun <T> safeApiRequest(block: () -> T): me.androidbox.domain.APIResponse<T> {
    return try {
        me.androidbox.domain.APIResponse.Success(data = block())
    }
    catch(exception: Exception) {
        me.androidbox.domain.APIResponse.Failure(error = exception)
    }
}