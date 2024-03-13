package me.androidbox.repository.userValidationRepository.usecases

import me.androidbox.APIResponse

fun interface RegisterUserWithEmailAndPasswordUseCase {
    suspend fun execute(email: String, password: String): APIResponse<String?>
}