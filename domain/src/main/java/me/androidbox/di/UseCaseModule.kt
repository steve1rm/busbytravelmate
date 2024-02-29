package me.androidbox.di

import me.androidbox.repository.userTokenRepository.FetchUserTokenUseCase
import me.androidbox.repository.userTokenRepository.SaveUserTokenUseCase
import me.androidbox.repository.userTokenRepository.UserTokenRepository
import me.androidbox.repository.userTokenRepository.imp.FetchUserTokenUseCaseImp
import me.androidbox.repository.userTokenRepository.imp.SaveUserTokenUseCaseImp
import org.koin.dsl.module

val useCaseModule = module {
    factory<SaveUserTokenUseCase> {
        SaveUserTokenUseCaseImp(this.get<UserTokenRepository>())
    }

    factory<FetchUserTokenUseCase> {
        FetchUserTokenUseCaseImp(this.get<UserTokenRepository>())
    }
}