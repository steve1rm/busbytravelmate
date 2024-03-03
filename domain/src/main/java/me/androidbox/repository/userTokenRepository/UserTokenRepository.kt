package me.androidbox.repository.userTokenRepository

import me.androidbox.APIResponse
import me.androidbox.model.UserTokenModel
import me.androidbox.model.UserTokenRequestModel

interface UserTokenRepository {
    suspend fun requestToken(userTokenRequestModel: UserTokenRequestModel): APIResponse<UserTokenModel>
    suspend fun saveUserToken(userToken: String)
    suspend fun fetchUserToken(): String?
}