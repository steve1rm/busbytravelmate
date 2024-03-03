package me.androidbox.repository.userTokenRepository.usecases.imp

import me.androidbox.APIResponse
import me.androidbox.model.UserTokenModel
import me.androidbox.model.UserTokenRequestModel
import me.androidbox.repository.userTokenRepository.UserTokenRepository
import me.androidbox.repository.userTokenRepository.usecases.RequestUserTokenUseCase

class RequestUserTokenUseCaseImp(private val userTokenRepository: UserTokenRepository): RequestUserTokenUseCase {
    override suspend fun execute(userTokenRequestModel: UserTokenRequestModel): APIResponse<UserTokenModel> {
       return userTokenRepository.requestUserToken(userTokenRequestModel)
    }
}
