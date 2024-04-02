package me.androidbox.repository.userValidationRepository.usecases.imp

import me.androidbox.APIResponse
import me.androidbox.repository.userValidationRepository.UserValidationRepository
import me.androidbox.repository.userValidationRepository.usecases.LoginUserWithEmailAndPasswordUseCase
import org.koin.core.annotation.Factory

@Factory
class LoginUserWithEmailAndPasswordUseCaseImp(private val userValidationRepository: UserValidationRepository) : LoginUserWithEmailAndPasswordUseCase {
    override suspend fun execute(email: String, password: String): APIResponse<String?> {
        return userValidationRepository.loginUser(email, password)
    }
}