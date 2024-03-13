package me.androidbox.repository.userValidationRepository.usecases

import me.androidbox.APIResponse

fun interface LoginUserWithEmailAndPasswordUseCase {
    suspend fun execute(email: String, password: String): APIResponse<String?>
}