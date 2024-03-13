package me.androidbox.repository.userValidationRepository.usecases

fun interface SaveUserTokenUseCase {
    suspend fun execute(userToken: String)
}
