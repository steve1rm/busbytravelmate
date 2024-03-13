package me.androidbox.repository.userValidationRepository.usecases

import me.androidbox.APIResponse
import me.androidbox.model.UserTokenModel
import me.androidbox.model.UserTokenRequestModel

fun interface RequestUserTokenUseCase {
    suspend fun execute(userTokenRequestModel: UserTokenRequestModel): APIResponse<UserTokenModel>
}
