package me.androidbox.repository.userTokenRepository.usecases.imp

import me.androidbox.repository.userTokenRepository.UserTokenRepository
import me.androidbox.repository.userTokenRepository.usecases.GetUserTokenUseCase

class GetUserTokenUseCaseImp(private val userTokenRepository: UserTokenRepository) :
    GetUserTokenUseCase {
    override suspend fun execute(): String? {
        return userTokenRepository.fetchUserToken()
    }
}
