package me.androidbox.repository.userValidationRepository

import me.androidbox.APIResponse
import me.androidbox.model.UserTokenModel
import me.androidbox.model.UserTokenRequestModel

interface UserValidationRepository {
    suspend fun requestUserToken(
        userTokenRequestModel: UserTokenRequestModel
    ): APIResponse<UserTokenModel>
    suspend fun saveUserToken(userToken: String)
    suspend fun fetchUserToken(): String?
    suspend fun loginUser(email: String, password: String): APIResponse<String?>
    suspend fun registerUser(email: String, password: String): APIResponse<String?>
    suspend fun logout(): APIResponse<Unit>
}
