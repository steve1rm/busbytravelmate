package me.androidbox.di

import me.androidbox.repository.userValidationRepository.UserValidationRepository
import me.androidbox.repository.userValidationRepository.usecases.GetUserTokenUseCase
import me.androidbox.repository.userValidationRepository.usecases.LoginUserWithEmailAndPasswordUseCase
import me.androidbox.repository.userValidationRepository.usecases.LogoutUseCase
import me.androidbox.repository.userValidationRepository.usecases.RegisterUserWithEmailAndPasswordUseCase
import me.androidbox.repository.userValidationRepository.usecases.RequestUserTokenUseCase
import me.androidbox.repository.userValidationRepository.usecases.SaveUserTokenUseCase
import me.androidbox.repository.userValidationRepository.usecases.imp.GetUserTokenUseCaseImp
import me.androidbox.repository.userValidationRepository.usecases.imp.LoginUserWithEmailAndPasswordUseCaseImp
import me.androidbox.repository.userValidationRepository.usecases.imp.LogoutUseCaseImp
import me.androidbox.repository.userValidationRepository.usecases.imp.RegisterUserWithEmailAndPasswordUseCaseImp
import me.androidbox.repository.userValidationRepository.usecases.imp.RequestUserTokenUseCaseImp
import me.androidbox.repository.userValidationRepository.usecases.imp.SaveUserTokenUseCaseImp
import org.koin.dsl.module

val useCaseModule = module {
    factory<SaveUserTokenUseCase> { SaveUserTokenUseCaseImp(this.get<UserValidationRepository>()) }

    factory<GetUserTokenUseCase> { GetUserTokenUseCaseImp(this.get<UserValidationRepository>()) }

    factory<RequestUserTokenUseCase> { RequestUserTokenUseCaseImp(this.get<UserValidationRepository>()) }

    factory<LoginUserWithEmailAndPasswordUseCase> { LoginUserWithEmailAndPasswordUseCaseImp(this.get<UserValidationRepository>()) }

    factory<RegisterUserWithEmailAndPasswordUseCase> { RegisterUserWithEmailAndPasswordUseCaseImp(this.get<UserValidationRepository>()) }

    factory<LogoutUseCase> { LogoutUseCaseImp(this.get<UserValidationRepository>()) }
}
