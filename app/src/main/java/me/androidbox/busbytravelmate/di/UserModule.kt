package me.androidbox.busbytravelmate.di

import androidx.lifecycle.SavedStateHandle
import me.androidbox.busbytravelmate.userValidation.viewmodels.UserValidationViewModel
import me.androidbox.repository.userValidationRepository.usecases.GetUserTokenUseCase
import me.androidbox.repository.userValidationRepository.usecases.RequestUserTokenUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {
    viewModel<UserValidationViewModel> {
        UserValidationViewModel(
            get<RequestUserTokenUseCase>(),
            get<GetUserTokenUseCase>(),
            get<SavedStateHandle>()
        )
    }
}
