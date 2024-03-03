package me.androidbox.repository.userTokenRepository.usecases.imp

import me.androidbox.model.UserTokenRequestModel
import me.androidbox.repository.userTokenRepository.usecases.FetchUserTokenUseCase
import me.androidbox.repository.userTokenRepository.UserTokenRepository

class FetchUserTokenUseCaseImp(private val userTokenRepository: UserTokenRepository) :
    FetchUserTokenUseCase {
    override suspend fun execute(userTokenRequestModel: UserTokenRequestModel): String? {
        return userTokenRepository.fetchUserToken()
    }
}