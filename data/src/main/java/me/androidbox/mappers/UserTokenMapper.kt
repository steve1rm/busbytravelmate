package me.androidbox.mappers

import me.androidbox.data.remote.dto.UserTokenDto
import me.androidbox.data.remote.dto.UserTokenRequestDto
import me.androidbox.model.UserTokenModel
import me.androidbox.model.UserTokenRequestModel

fun UserTokenDto.toUserTokenModel(): UserTokenModel {
    return UserTokenModel(
        accessToken = this.accessToken,
        applicationName = this.applicationName,
        clientId = this.clientId,
        expiresIn = this.expiresIn,
        scope = this.scope,
        state = this.state,
        tokenType = this.tokenType,
        type = this.type,
        username = this.username
    )
}

fun UserTokenRequestModel.toUserTokenRequestDto(): UserTokenRequestDto {
    return UserTokenRequestDto(
        grantType = this.grantType,
        clientId = this.clientId,
        clientSecret = this.clientSecret
    )
}
