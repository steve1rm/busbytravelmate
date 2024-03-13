package me.androidbox.repository.userValidationRepository.usecases.imp

import me.androidbox.repository.userValidationRepository.UserValidationRepository
import me.androidbox.repository.userValidationRepository.usecases.SaveUserTokenUseCase

class SaveUserTokenUseCaseImp(private val userValidationRepository: UserValidationRepository) :
    SaveUserTokenUseCase {
    override suspend fun execute(userToken: String) {
        userValidationRepository.saveUserToken(userToken)
    }
}
