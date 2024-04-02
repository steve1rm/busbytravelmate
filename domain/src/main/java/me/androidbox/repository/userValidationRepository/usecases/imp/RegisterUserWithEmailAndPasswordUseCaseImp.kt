package me.androidbox.repository.userValidationRepository.usecases.imp

import me.androidbox.APIResponse
import me.androidbox.repository.userValidationRepository.UserValidationRepository
import me.androidbox.repository.userValidationRepository.usecases.RegisterUserWithEmailAndPasswordUseCase
import org.koin.core.annotation.Factory

@Factory
class RegisterUserWithEmailAndPasswordUseCaseImp(private val userValidationRepository: UserValidationRepository) : RegisterUserWithEmailAndPasswordUseCase {
    override suspend fun execute(email: String, password: String): APIResponse<String?> {
        return userValidationRepository.registerUser(email, password)
    }
}