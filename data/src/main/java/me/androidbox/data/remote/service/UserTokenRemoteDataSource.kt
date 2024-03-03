package me.androidbox.data.remote.service

import me.androidbox.APIResponse
import me.androidbox.data.remote.dto.UserTokenDto
import me.androidbox.data.remote.dto.UserTokenRequestDto
import me.androidbox.model.UserTokenModel

interface UserTokenRemoteDataSource {
    suspend fun requestUserToken(tokenRequest: UserTokenRequestDto): APIResponse<UserTokenDto>
}