package me.androidbox.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserTokenDto(
    @SerialName("access_token") val accessToken: String,
    @SerialName("application_name") val applicationName: String,
    @SerialName("client_id") val clientId: String,
    @SerialName("expires_in") val expiresIn: Int,
    val scope: String,
    val state: String,
    @SerialName("token_type") val tokenType: String,
    val type: String,
    val username: String
)
