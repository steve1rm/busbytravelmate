package me.androidbox.busbytravelmate.di

import androidx.lifecycle.viewmodel.compose.viewModel
import me.androidbox.busbytravelmate.userTokenRepository.viewmodels.UserRepositoryViewModel
import me.androidbox.repository.userTokenRepository.usecases.GetUserTokenUseCase
import me.androidbox.repository.userTokenRepository.usecases.RequestUserTokenUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {
    viewModel<UserRepositoryViewModel> {
        UserRepositoryViewModel(get<RequestUserTokenUseCase>(), get<GetUserTokenUseCase>())
    }
}
