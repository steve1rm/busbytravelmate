package me.androidbox.data.remote.service

import me.androidbox.data.remote.dto.TokenRequest
import me.androidbox.data.remote.dto.TokenResponse
import me.androidbox.APIResponse

interface BusbyTravelMateService {

    suspend fun requestToken(tokenRequest: TokenRequest): APIResponse<TokenResponse>
}