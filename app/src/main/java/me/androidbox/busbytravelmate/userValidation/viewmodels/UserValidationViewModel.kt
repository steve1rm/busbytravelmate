package me.androidbox.busbytravelmate.userValidation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.androidbox.busbytravelmate.mappers.toUserTokenRequestModel
import me.androidbox.busbytravelmate.model.UserTokenRequest
import me.androidbox.busbytravelmate.userValidation.viewstate.UserValidationEvents
import me.androidbox.busbytravelmate.userValidation.viewstate.UserValidationState
import me.androidbox.repository.userValidationRepository.usecases.GetUserTokenUseCase
import me.androidbox.repository.userValidationRepository.usecases.LoginUserWithEmailAndPasswordUseCase
import me.androidbox.repository.userValidationRepository.usecases.RegisterUserWithEmailAndPasswordUseCase
import me.androidbox.repository.userValidationRepository.usecases.RequestUserTokenUseCase
import timber.log.Timber

class UserValidationViewModel(
    private val requestUserTokenUseCase: RequestUserTokenUseCase,
    private val getUserTokenUseCase: GetUserTokenUseCase,
    private val registerUserWithEmailAndPasswordUseCase: RegisterUserWithEmailAndPasswordUseCase,
    private val loginUserWithEmailAndPasswordUseCase: LoginUserWithEmailAndPasswordUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

     val userValidationState = savedStateHandle.getStateFlow("userValidationState", UserValidationState<Unit>())

    fun validationEvents(userValidationEvents: UserValidationEvents) {
        when(userValidationEvents) {
            is UserValidationEvents.OnUserTokenRequest -> {
                savedStateHandle["userValidationState"] =
                    userValidationState.value.copy(userToken = userValidationEvents.userToken)

            }

            is UserValidationEvents.OnEmailChanged -> {
                savedStateHandle["email"] = userValidationEvents.email
            }
            is UserValidationEvents.OnLoading -> {
            /*    _userValidationState.update { userValidationState ->
                    userValidationState.copy(isLoading = userValidationEvents.isLoading)
                }*/
            }
            UserValidationEvents.OnLoginClicked -> {
                TODO()
            }
            is UserValidationEvents.OnPasswordChanged -> TODO()
            is UserValidationEvents.OnPasswordVisibilityChanged -> TODO()
            UserValidationEvents.OnSignUpClicked -> TODO()
        }
    }

    fun loginClicked() {
        viewModelScope.launch {
            loginUserWithEmailAndPasswordUseCase.execute("test@mail.com", "123456")
        }
    }

    fun register() {
        viewModelScope.launch {
            registerUserWithEmailAndPasswordUseCase.execute("test@mail.com", "123456")
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