package me.androidbox.model

data class UserTokenModel(
    val accessToken: String,
    val applicationName: String,
    val clientId: String,
    val expiresIn: Int,
    val scope: String,
    val state: String,
    val tokenType: String,
    val type: String,
    val username: String
)
