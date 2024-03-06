package me.androidbox.repository.userTokenRepository.usecases.imp

import me.androidbox.repository.userTokenRepository.UserTokenRepository
import me.androidbox.repository.userTokenRepository.usecases.SaveUserTokenUseCase

class SaveUserTokenUseCaseImp(private val userTokenRepository: UserTokenRepository) :
    SaveUserTokenUseCase {
    override suspend fun execute(userToken: String) {
        userTokenRepository.saveUserToken(userToken)
    }
}
