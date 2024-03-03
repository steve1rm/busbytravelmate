package me.androidbox.repository.userTokenRepository.usecases

import me.androidbox.model.UserTokenRequestModel

fun interface FetchUserTokenUseCase {
    suspend fun execute(userTokenRequestModel: UserTokenRequestModel): String?
}