package me.androidbox.repository.userTokenRepository.imp

import me.androidbox.repository.userTokenRepository.FetchUserTokenUseCase
import me.androidbox.repository.userTokenRepository.UserTokenRepository

class FetchUserTokenUseCaseImp(private val userTokenRepository: UserTokenRepository) : FetchUserTokenUseCase {
    override suspend fun execute(): String? {
        return userTokenRepository.fetchUserToken()
    }
}