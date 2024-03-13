package me.androidbox.di

import me.androidbox.repository.userValidationRepository.UserValidationRepository
import me.androidbox.repository.userValidationRepository.usecases.GetUserTokenUseCase
import me.androidbox.repository.userValidationRepository.usecases.RequestUserTokenUseCase
import me.androidbox.repository.userValidationRepository.usecases.SaveUserTokenUseCase
import me.androidbox.repository.userValidationRepository.usecases.imp.GetUserTokenUseCaseImp
import me.androidbox.repository.userValidationRepository.usecases.imp.RequestUserTokenUseCaseImp
import me.androidbox.repository.userValidationRepository.usecases.imp.SaveUserTokenUseCaseImp
import me.androidbox.repository.userValidationRepository.usecases.imp.UserLoginUseCase
import me.androidbox.repository.userValidationRepository.usecases.imp.UserLoginUseCaseImp
import org.koin.dsl.module

val useCaseModule = module {
    factory<SaveUserTokenUseCase> { SaveUserTokenUseCaseImp(this.get<UserValidationRepository>()) }

    factory<GetUserTokenUseCase> { GetUserTokenUseCaseImp(this.get<UserValidationRepository>()) }

    factory<RequestUserTokenUseCase> { RequestUserTokenUseCaseImp(this.get<UserValidationRepository>()) }

    factory<UserLoginUseCase> { UserLoginUseCaseImp(this.get<UserValidationRepository>()) }
}
