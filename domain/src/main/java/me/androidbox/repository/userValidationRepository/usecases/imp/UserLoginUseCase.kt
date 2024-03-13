package me.androidbox.repository.userValidationRepository.usecases.imp

import me.androidbox.APIResponse

interface UserLoginUseCase {
    suspend fun UserLogin(email: String, password: String): APIResponse<String>
}