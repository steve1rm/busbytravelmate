package me.androidbox.repository.userTokenRepository

fun interface FetchUserTokenUseCase {
    suspend fun execute(): String?
}