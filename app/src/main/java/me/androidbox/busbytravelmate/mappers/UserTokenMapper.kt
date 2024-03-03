package me.androidbox.busbytravelmate.mappers

import me.androidbox.busbytravelmate.model.UserTokenRequest
import me.androidbox.model.UserTokenRequestModel

fun UserTokenRequest.toUserTokenRequestModel(): UserTokenRequestModel {
    return UserTokenRequestModel(
        grantType = this.grantType,
        clientId = this.clientId,
        clientSecret = this.clientSecret
    )
}