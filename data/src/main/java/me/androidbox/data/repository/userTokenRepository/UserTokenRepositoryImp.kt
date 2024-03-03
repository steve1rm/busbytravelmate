package me.androidbox.data.repository.userTokenRepository
import me.androidbox.APIResponse
import me.androidbox.data.local.UserTokenLocalDataSource
import me.androidbox.data.remote.service.UserTokenRemoteDataSource
import me.androidbox.mappers.toUserTokenRequestDto
import me.androidbox.model.UserTokenModel
import me.androidbox.model.UserTokenRequestModel
import me.androidbox.repository.userTokenRepository.UserTokenRepository
import timber.log.Timber

class UserTokenRepositoryImp(
    private val userTokenLocalDataSource: UserTokenLocalDataSource,
    private val userTokenRemoteDataSource: UserTokenRemoteDataSource) : UserTokenRepository {

    override suspend fun requestToken(userTokenRequestModel: UserTokenRequestModel): APIResponse<UserTokenModel> {
        return userTokenRemoteDataSource.requestToken(userTokenRequestModel.toUserTokenRequestDto())
            .onSuccess { userTokenDto ->
                saveUserToken(userToken = userTokenDto.accessToken)
            }
            .onFailure { exception ->
                Timber.e(exception)
            }
    }

    override suspend fun saveUserToken(userToken: String) {
        userTokenLocalDataSource.saveUserToken(userToken)
    }

    override suspend fun fetchUserToken(): String? {
        return userTokenLocalDataSource.fetchUserToken()
    }
}