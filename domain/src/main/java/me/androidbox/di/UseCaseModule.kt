package me.androidbox.di

import me.androidbox.repository.userTokenRepository.UserTokenRepository
import me.androidbox.repository.userTokenRepository.usecases.GetUserTokenUseCase
import me.androidbox.repository.userTokenRepository.usecases.RequestUserTokenUseCase
import me.androidbox.repository.userTokenRepository.usecases.SaveUserTokenUseCase
import me.androidbox.repository.userTokenRepository.usecases.imp.GetUserTokenUseCaseImp
import me.androidbox.repository.userTokenRepository.usecases.imp.RequestUserTokenUseCaseImp
import me.androidbox.repository.userTokenRepository.usecases.imp.SaveUserTokenUseCaseImp
import org.koin.dsl.module

val useCaseModule = module {
    factory<SaveUserTokenUseCase> { SaveUserTokenUseCaseImp(this.get<UserTokenRepository>()) }

    factory<GetUserTokenUseCase> { GetUserTokenUseCaseImp(this.get<UserTokenRepository>()) }

    factory<RequestUserTokenUseCase> { RequestUserTokenUseCaseImp(this.get<UserTokenRepository>()) }
}
