package me.androidbox.data.repository.userTokenRepository
import me.androidbox.data.local.UserTokenLocalDataSource
import me.androidbox.repository.userTokenRepository.UserTokenRepository

class UserTokenRepositoryImp(
    private val userTokenLocalDataSource: UserTokenLocalDataSource) : UserTokenRepository {

    override suspend fun saveUserToken(userToken: String) {
        userTokenLocalDataSource.saveUserToken(userToken)
    }

    override suspend fun retrieveUserToken(): String? {
        return userTokenLocalDataSource.retrieveUserToken()
    }
}