package me.androidbox.repository.userValidationRepository.usecases.imp

import me.androidbox.APIResponse
import me.androidbox.model.UserTokenModel
import me.androidbox.model.UserTokenRequestModel
import me.androidbox.repository.userValidationRepository.UserValidationRepository
import me.androidbox.repository.userValidationRepository.usecases.RequestUserTokenUseCase

class RequestUserTokenUseCaseImp(private val userTokenRepository: UserValidationRepository) :
    RequestUserTokenUseCase {
    override suspend fun execute(
        userTokenRequestModel: UserTokenRequestModel
    ): APIResponse<UserTokenModel> {
        return userTokenRepository.requestUserToken(userTokenRequestModel)
    }
}
