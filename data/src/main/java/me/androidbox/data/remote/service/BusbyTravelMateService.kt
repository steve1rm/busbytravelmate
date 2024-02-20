package me.androidbox.data.remote.service

import me.androidbox.data.remote.dto.TokenRequest
import me.androidbox.data.remote.dto.TokenResponse

interface BusbyTravelMateService {

    suspend fun requestToken(tokenRequest: TokenRequest): TokenResponse
}