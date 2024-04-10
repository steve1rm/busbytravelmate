package me.androidbox.busbytravelmate.di

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import me.androidbox.busbytravelmate.userValidation.viewmodels.TestKoinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Factory
import org.koin.dsl.module


@Module
@ComponentScan
class TestKoinViewModelModule {
    @Factory
    fun provideSavedStateHandle(): SavedStateHandle {
        return SavedStateHandle()
    }
}

