package me.androidbox.busbytravelmate.userValidation.viewstate

sealed interface UserValidationEvents {
    data class OnUserTokenRequest(val userToken: String) : UserValidationEvents
}
