package me.androidbox.busbytravelmate.userValidation.viewstate

sealed interface UserValidationEvents {
    data class OnUserTokenRequest(val userToken: String) : UserValidationEvents
    data class OnLoginClicked(val email: String, val password: String) : UserValidationEvents
    data object OnLoginSuccess : UserValidationEvents
    data class OnLoginFailure(val errorMessage: String) : UserValidationEvents
    data class OnSignInClicked(val email: String, val password: String) : UserValidationEvents
    data object OnSignUpClicked : UserValidationEvents
    object OnErrorMessageSeen : UserValidationEvents
    data class OnEmailChanged(val email: String) : UserValidationEvents
    data class OnPasswordChanged(val password: String) : UserValidationEvents
    data object OnPasswordVisibilityChanged : UserValidationEvents
    data class OnLoading(val isLoading: Boolean? = null) : UserValidationEvents
}