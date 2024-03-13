package me.androidbox.repository.userValidationRepository.usecases

fun interface GetUserTokenUseCase {
    suspend fun execute(): String?
}
