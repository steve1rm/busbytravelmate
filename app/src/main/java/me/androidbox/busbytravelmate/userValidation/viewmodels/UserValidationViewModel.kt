package me.androidbox.busbytravelmate.userValidation.viewmodels

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.androidbox.APIResponse
import me.androidbox.busbytravelmate.mappers.toUserTokenRequestModel
import me.androidbox.busbytravelmate.model.UserTokenRequest
import me.androidbox.busbytravelmate.userValidation.viewstate.UserValidationEvents
import me.androidbox.busbytravelmate.userValidation.viewstate.UserValidationState
import me.androidbox.repository.userTokenRepository.usecases.GetUserTokenUseCase
import me.androidbox.repository.userTokenRepository.usecases.RequestUserTokenUseCase
import timber.log.Timber

class UserValidationViewModel(
    private val requestUserTokenUseCase: RequestUserTokenUseCase,
    private val getUserTokenUseCase: GetUserTokenUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

 //   private val _userValidationState = MutableStateFlow(UserValidationState())
    val userValidationState = savedStateHandle.getStateFlow("userValidationState", UserValidationState())
    val userTokenState = savedStateHandle.getStateFlow("userToken", "")

    fun validationEvents(userValidationEvents: UserValidationEvents) {
        when(userValidationEvents) {
            is UserValidationEvents.OnUserTokenRequest -> {
                savedStateHandle["userValidationState"] =
                    userValidationState.value.copy(userToken = userValidationEvents.userToken)

                savedStateHandle["userToken"] = userValidationEvents.userToken
            }

            is UserValidationEvents.OnEmailChanged -> {
                savedStateHandle["email"] = userValidationEvents.email
            }
            is UserValidationEvents.OnLoading -> {
            /*    _userValidationState.update { userValidationState ->
                    userValidationState.copy(isLoading = userValidationEvents.isLoading)
                }*/
            }
            UserValidationEvents.OnLoginClicked -> TODO()
            is UserValidationEvents.OnPasswordChanged -> TODO()
            is UserValidationEvents.OnPasswordVisibilityChanged -> TODO()
            UserValidationEvents.OnSignUpClicked -> TODO()
        }
    }

    fun requestUserToken() {
        viewModelScope.launch {
            requestUserTokenUseCase.execute(
                UserTokenRequest(
                    grantType = "client_credentials",
                    clientId = "p8ioeKrMrtQkeOD8yuUjqtxaYG4Nt2KB",
                    clientSecret = "PGDukHIYKweKbYob"
                ).toUserTokenRequestModel()
            )
                .onSuccess { userTokenModel ->
                    Timber.d(userTokenModel.toString())
                    Timber.d(getUserTokenUseCase.execute())
                    validationEvents(UserValidationEvents.OnUserTokenRequest(userToken = userTokenModel.accessToken))
                }
                .onFailure {
                    Timber.e(it.message)
                }
        }
    }
}