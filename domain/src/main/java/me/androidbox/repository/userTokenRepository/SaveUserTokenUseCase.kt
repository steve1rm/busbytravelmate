package me.androidbox.repository.userTokenRepository

fun interface SaveUserTokenUseCase {
    suspend fun execute(userToken: String)
}