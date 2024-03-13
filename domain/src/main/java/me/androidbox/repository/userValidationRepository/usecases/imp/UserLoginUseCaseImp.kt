package me.androidbox.repository.userValidationRepository.usecases.imp

import me.androidbox.APIResponse
import me.androidbox.repository.userValidationRepository.UserValidationRepository

class UserLoginUseCaseImp(private val userValidationRepository: UserValidationRepository) : UserLoginUseCase {
    override suspend fun UserLogin(email: String, password: String): APIResponse<String> {
        return userValidationRepository.loginUser(email, password)
    }
}