package me.androidbox.repository.userTokenRepository

interface UserTokenRepository {
    suspend fun saveUserToken(userToken: String)
    suspend fun fetchUserToken(): String?
}