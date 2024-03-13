package me.androidbox.data.repository.userValidationRepository

import me.androidbox.APIResponse
import me.androidbox.data.local.UserTokenLocalDataSource
import me.androidbox.data.remote.service.UserLoginRegisterRemoteDataSource
import me.androidbox.data.remote.service.UserTokenRemoteDataSource
import me.androidbox.mappers.toUserTokenModel
import me.androidbox.mappers.toUserTokenRequestDto
import me.androidbox.model.UserTokenModel
import me.androidbox.model.UserTokenRequestModel
import me.androidbox.repository.userValidationRepository.UserValidationRepository

class UserValidationRepositoryImp(
    private val userTokenLocalDataSource: UserTokenLocalDataSource,
    private val userTokenRemoteDataSource: UserTokenRemoteDataSource,
    private val userLoginRegisterRemoteDataSource: UserLoginRegisterRemoteDataSource
) : UserValidationRepository {

    override suspend fun requestUserToken(
        userTokenRequestModel: UserTokenRequestModel
    ): APIResponse<UserTokenModel> {
        val apiResponse =
            userTokenRemoteDataSource.requestUserToken(
                userTokenRequestModel.toUserTokenRequestDto()
            )

        return when (apiResponse) {
            is APIResponse.Success -> {
                saveUserToken(apiResponse.data.accessToken)
                APIResponse.Success(apiResponse.data.toUserTokenModel())
            }
            is APIResponse.Failure -> {
                APIResponse.Failure(apiResponse.error)
            }

            APIResponse.IsLoading -> TODO()
        }
    }

    override suspend fun saveUserToken(userToken: String) {
        userTokenLocalDataSource.saveUserToken(userToken)
    }

    override suspend fun fetchUserToken(): String? {
        return userTokenLocalDataSource.fetchUserToken()
    }

    override suspend fun loginUser(email: String, password: String): APIResponse<String?> {
        return userLoginRegisterRemoteDataSource.loginUser(email, password)
    }

    override suspend fun registerUser(email: String, password: String): APIResponse<String?> {
        return userLoginRegisterRemoteDataSource.registerUser(email, password)
    }
}
