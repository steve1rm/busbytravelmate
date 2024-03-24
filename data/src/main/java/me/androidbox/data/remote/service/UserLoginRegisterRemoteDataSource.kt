package me.androidbox.data.remote.service

import me.androidbox.APIResponse

interface UserLoginRegisterRemoteDataSource {
    suspend fun loginUser(email: String, password: String): APIResponse<String?>
    suspend fun registerUser(email: String, password: String): APIResponse<String?>
    suspend fun logout(): APIResponse<Unit>
    suspend fun isLoggedIn(): APIResponse<Boolean>
}