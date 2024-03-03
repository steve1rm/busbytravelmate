package me.androidbox.model

data class UserTokenRequestModel(
    val grantType: String,
    val clientId: String,
    val clientSecret: String
)
