package me.androidbox.data.remote.service

import me.androidbox.data.remote.dto.TokenRequest
import me.androidbox.data.remote.dto.TokenResponse
import me.androidbox.domain.APIResponse

interface BusbyTravelMateService {

    suspend fun requestToken(tokenRequest: TokenRequest): me.androidbox.domain.APIResponse<TokenResponse>
}