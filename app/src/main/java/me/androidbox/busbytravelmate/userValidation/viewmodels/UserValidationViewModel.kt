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
import me.androidbox.repository.userValidationRepository.usecases.LogoutUseCase
import me.androidbox.repository.userValidationRepository.usecases.RegisterUserWithEmailAndPasswordUseCase
import me.androidbox.repository.userValidationRepository.usecases.RequestUserTokenUseCase
import org.koin.android.annotation.KoinViewModel
import timber.log.Timber

class UserValidationViewModel(
    private val requestUserTokenUseCase: RequestUserTokenUseCase,
    private val getUserTokenUseCase: GetUserTokenUseCase,
    private val registerUserWithEmailAndPasswordUseCase: RegisterUserWithEmailAndPasswordUseCase,
    private val loginUserWithEmailAndPasswordUseCase: LoginUserWithEmailAndPasswordUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private companion object {
        const val USER_VALIDATION_STATE = "userValidationState"
    }

    val userValidationState =
        savedStateHandle.getStateFlow(USER_VALIDATION_STATE, UserValidationState<Unit>())

    fun validationEvents(userValidationEvent: UserValidationEvents) {
        when(userValidationEvent) {
            is UserValidationEvents.OnUserTokenRequest -> {
                savedStateHandle[USER_VALIDATION_STATE] =
                    userValidationState.value.copy(userToken = userValidationEvent.userToken)
            }

            is UserValidationEvents.OnEmailChanged -> {
                savedStateHandle[USER_VALIDATION_STATE] =
                    userValidationState.value.copy(email = userValidationEvent.email)
            }
            is UserValidationEvents.OnLoading -> {
                savedStateHandle[USER_VALIDATION_STATE] =
                    userValidationState.value.copy(
                        isLoading = userValidationEvent.isLoading == true)
            }
            is UserValidationEvents.OnPasswordChanged -> {
                savedStateHandle[USER_VALIDATION_STATE] =
                    userValidationState.value.copy(password = userValidationEvent.password)
            }
            is UserValidationEvents.OnSignInClicked -> {
                register(userValidationEvent.email, userValidationEvent.password)
            }
            is UserValidationEvents.OnLoginClicked -> {
                validationEvents(UserValidationEvents.OnLoading(isLoading = true))
                login(userValidationEvent.email, userValidationEvent.password)
            }

            UserValidationEvents.OnSignUpClicked -> {
                TODO()
            }

            is UserValidationEvents.OnLoginFailure -> {
                validationEvents(UserValidationEvents.OnLoading(isLoading = false))
                savedStateHandle[USER_VALIDATION_STATE] = userValidationState.value.copy(
                    isLoginSuccess = false,
                    errorMessage = userValidationEvent.errorMessage
                )
            }

            UserValidationEvents.OnLoginSuccess -> {
                validationEvents(UserValidationEvents.OnLoading(isLoading = false))
                savedStateHandle[USER_VALIDATION_STATE] = userValidationState.value.copy(
                    isLoginSuccess = true,
                    errorMessage = ""
                )
            }

            UserValidationEvents.OnErrorMessageSeen -> {
                savedStateHandle[USER_VALIDATION_STATE] = userValidationState.value.copy(
                    isLoginSuccess = false,
                    errorMessage = "")
            }
        }
    }

    // "test1@mail.com", "123456"
    private fun register(email: String, password: String) {
        viewModelScope.launch {
            registerUserWithEmailAndPasswordUseCase.execute(email, password)
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUserWithEmailAndPasswordUseCase.execute(email, password)
                .onSuccess {
                    validationEvents(UserValidationEvents.OnLoginSuccess)
                }
                .onFailure {
                    validationEvents(UserValidationEvents.OnLoginFailure(it.message ?: "Unknown"))
                }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            logoutUseCase.execute()
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