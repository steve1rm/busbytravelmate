package me.androidbox.repository.userTokenRepository.usecases.imp

import me.androidbox.repository.userTokenRepository.usecases.SaveUserTokenUseCase
import me.androidbox.repository.userTokenRepository.UserTokenRepository

class SaveUserTokenUseCaseImp(private val userTokenRepository: UserTokenRepository) :
    SaveUserTokenUseCase {
    override suspend fun execute(userToken: String) {
       userTokenRepository.saveUserToken(userToken)
    }
}