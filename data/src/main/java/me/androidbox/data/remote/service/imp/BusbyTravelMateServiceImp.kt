package me.androidbox.data.remote.service.imp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.ContentType.Application.FormUrlEncoded
import io.ktor.http.Parameters
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import me.androidbox.data.remote.dto.TokenRequest
import me.androidbox.data.remote.dto.TokenResponse
import me.androidbox.data.remote.service.BusbyTravelMateService
import me.androidbox.data.remote.service.Routes.TOKEN_URL
import me.androidbox.APIResponse
import me.androidbox.safeApiRequest

class BusbyTravelMateServiceImp(
    private val httpClient: HttpClient
) : BusbyTravelMateService {

    override suspend fun requestToken(tokenRequest: TokenRequest): APIResponse<TokenResponse> {
        val requestBody = Parameters.build {
            append("grant_type", tokenRequest.grantType)
            append("client_id", tokenRequest.clientId)
            append("client_secret", tokenRequest.clientSecret)
        }

        return safeApiRequest {
            httpClient
                .post(TOKEN_URL) {
                    setBody(FormDataContent(requestBody))
                    contentType(FormUrlEncoded)
                    accept(ContentType.Application.Json)
                }
                .body<TokenResponse>()
        }
    }

    /* testing -- smaple */
    suspend fun fetchData(): String {
        return withContext(Dispatchers.IO) {
            delay(5000L)
            "Hello World!"
        }
    }
}