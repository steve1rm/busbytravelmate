package me.androidbox.repository.userValidationRepository.usecases.imp

import me.androidbox.repository.userValidationRepository.UserValidationRepository
import me.androidbox.repository.userValidationRepository.usecases.GetUserTokenUseCase

class GetUserTokenUseCaseImp(private val userValidationRepository: UserValidationRepository) :
    GetUserTokenUseCase {
    override suspend fun execute(): String? {
        return userValidationRepository.fetchUserToken()
    }
}
