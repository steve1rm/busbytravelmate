package me.androidbox.repository.userTokenRepository.usecases

fun interface GetUserTokenUseCase {
    suspend fun execute(): String?
}
