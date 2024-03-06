package me.androidbox.busbytravelmate.userTokenRepository.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.androidbox.busbytravelmate.mappers.toUserTokenRequestModel
import me.androidbox.busbytravelmate.model.UserTokenRequest
import me.androidbox.repository.userTokenRepository.usecases.GetUserTokenUseCase
import me.androidbox.repository.userTokenRepository.usecases.RequestUserTokenUseCase
import timber.log.Timber

class UserRepositoryViewModel(
    private val requestUserTokenUseCase: RequestUserTokenUseCase,
    private val getUserTokenUseCase: GetUserTokenUseCase
) : ViewModel() {



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
                }
                .onFailure {
                    Timber.e(it.message)
                }
        }
    }
}