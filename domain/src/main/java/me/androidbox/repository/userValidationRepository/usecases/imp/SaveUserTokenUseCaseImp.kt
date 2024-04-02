package me.androidbox.repository.userValidationRepository.usecases.imp

import me.androidbox.repository.userValidationRepository.UserValidationRepository
import me.androidbox.repository.userValidationRepository.usecases.SaveUserTokenUseCase
import org.koin.core.annotation.Factory

@Factory
class SaveUserTokenUseCaseImp(private val userValidationRepository: UserValidationRepository) :
    SaveUserTokenUseCase {
    override suspend fun execute(userToken: String) {
        userValidationRepository.saveUserToken(userToken)
    }
}
