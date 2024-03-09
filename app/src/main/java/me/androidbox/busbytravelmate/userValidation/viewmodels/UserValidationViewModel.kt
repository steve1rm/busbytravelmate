package me.androidbox.busbytravelmate.userValidation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
    private val savedStateHandle: SavedStateHandle = SavedStateHandle()
) : ViewModel() {

    private val _userValidationState = MutableStateFlow(UserValidationState())
    val userValidationState = _userValidationState.asStateFlow()

    fun events(userValidationEvents: UserValidationEvents) {
        when(userValidationEvents) {
            is UserValidationEvents.OnUserTokenRequest -> {
                _userValidationState.update { userValidationState ->
                    userValidationState.copy(userToken = userValidationEvents.userToken)
                }
            }
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
                    events(UserValidationEvents.OnUserTokenRequest(userToken = userTokenModel.accessToken))
                }
                .onFailure {
                    Timber.e(it.message)
                }
        }
    }
}