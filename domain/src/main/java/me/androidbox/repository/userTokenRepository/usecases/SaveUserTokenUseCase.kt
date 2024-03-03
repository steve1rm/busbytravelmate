package me.androidbox.repository.userTokenRepository.usecases

fun interface SaveUserTokenUseCase {
    suspend fun execute(userToken: String)
}