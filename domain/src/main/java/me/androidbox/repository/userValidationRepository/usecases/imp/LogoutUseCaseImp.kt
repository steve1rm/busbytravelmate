package me.androidbox.repository.userValidationRepository.usecases.imp

import me.androidbox.APIResponse
import me.androidbox.repository.userValidationRepository.UserValidationRepository
import me.androidbox.repository.userValidationRepository.usecases.LogoutUseCase

class LogoutUseCaseImp(private val userValidationRepository: UserValidationRepository) : LogoutUseCase {
    override suspend fun execute(): APIResponse<Unit> {
        return userValidationRepository.logout()
    }
}