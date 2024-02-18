package me.androidbox.data.remote.service.imp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.http.ContentType.Application.FormUrlEncoded
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import me.androidbox.data.remote.dto.TokenRequest
import me.androidbox.data.remote.dto.TokenResponse
import me.androidbox.data.remote.service.BusbyTravelMateService
import me.androidbox.data.remote.service.Routes.TOKEN_URL

class BusbyTravelMateServiceImp(
    private val httpClient: HttpClient
) : BusbyTravelMateService {

    @OptIn(InternalAPI::class)
    override suspend fun requestToken(tokenRequest: TokenRequest): TokenResponse {
        val requestBody = "grant_type=${tokenRequest.grantType}&client_id=${tokenRequest.clientId}&client_secret=${tokenRequest.clientSecret}"

        val responseToken = httpClient
            .post(TOKEN_URL) {
                body = requestBody
                contentType(FormUrlEncoded)
            }
            .body<TokenResponse>()

        return responseToken
    }
}