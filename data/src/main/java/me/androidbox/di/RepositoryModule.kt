package me.androidbox.di

import com.google.firebase.FirebaseApp
import me.androidbox.data.local.UserTokenLocalDataSource
import me.androidbox.data.local.imp.UserTokenLocalDataSourceImp
import me.androidbox.data.remote.service.UserLoginRegisterRemoteDataSource
import me.androidbox.data.remote.service.UserTokenRemoteDataSource
import me.androidbox.data.remote.service.imp.UserLoginRegisterRemoteDataSourceImp
import me.androidbox.data.repository.userValidationRepository.UserValidationRepositoryImp
import me.androidbox.repository.userValidationRepository.UserValidationRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<UserValidationRepository> {
        UserValidationRepositoryImp(
            this.get<UserTokenLocalDataSource>(),
            this.get<UserTokenRemoteDataSource>(),
            this.get<UserLoginRegisterRemoteDataSource>()
        )
    }

    single<UserTokenLocalDataSource> { UserTokenLocalDataSourceImp(androidContext()) }

    single<UserLoginRegisterRemoteDataSource> {
        UserLoginRegisterRemoteDataSourceImp()
    }
}
