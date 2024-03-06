package me.androidbox.data.remote.service

import me.androidbox.APIResponse
import me.androidbox.data.remote.dto.UserTokenDto
import me.androidbox.data.remote.dto.UserTokenRequestDto

interface UserTokenRemoteDataSource {
    suspend fun requestUserToken(tokenRequest: UserTokenRequestDto): APIResponse<UserTokenDto>
}
