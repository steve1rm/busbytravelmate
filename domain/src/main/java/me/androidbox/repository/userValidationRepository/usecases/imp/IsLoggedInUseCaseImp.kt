package me.androidbox.repository.userValidationRepository.usecases.imp

import me.androidbox.APIResponse
import me.androidbox.repository.userValidationRepository.UserValidationRepository
import me.androidbox.repository.userValidationRepository.usecases.IsLoggedInUseCase
import org.koin.core.annotation.Factory

class IsLoggedInUseCaseImp(private val userValidationRepository: UserValidationRepository) : IsLoggedInUseCase {
    override suspend fun execute(): APIResponse<Boolean> {
        return userValidationRepository.isLoggedIn()
    }
}
