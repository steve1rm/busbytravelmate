package me.androidbox.busbytravelmate.userValidation.viewstate

sealed interface UserValidationEvents {
    data class OnUserTokenRequest(val userToken: String) : UserValidationEvents
    data object OnLoginClicked : UserValidationEvents
    data object OnSignUpClicked : UserValidationEvents
    data class OnEmailChanged(val email: String) : UserValidationEvents
    data class OnPasswordChanged(val password: String) : UserValidationEvents
    data class OnPasswordVisibilityChanged(val isVisible: Boolean) : UserValidationEvents
    data class OnLoading(val isLoading: Boolean) : UserValidationEvents
}
