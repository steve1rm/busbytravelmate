package me.androidbox.data.local

interface UserTokenLocalDataSource {
    suspend fun saveUserToken(token: String)
    suspend fun retrieveUserToken(): String?
}