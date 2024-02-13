package me.androidbox.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenRequest(
    @SerialName("grant_type")
    val grantType: String,
    @SerialName("client_id")
    val clientId: String,
    @SerialName("client_secret")
    val clientSecret: String
)
//grant_type=client_credentials&client_id=p8ioeKrMrtQkeOD8yuUjqtxaYG4Nt2KB&client_secret=PGDukHIYKweKbYob"