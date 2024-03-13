package me.androidbox.di

import me.androidbox.data.local.UserTokenLocalDataSource
import me.androidbox.data.local.imp.UserTokenLocalDataSourceImp
import me.androidbox.data.remote.service.UserTokenRemoteDataSource
import me.androidbox.data.repository.userTokenRepository.UserTokenRepositoryImp
import me.androidbox.repository.userValidationRepository.UserValidationRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<UserValidationRepository> {
        UserTokenRepositoryImp(
            this.get<UserTokenLocalDataSource>(),
            this.get<UserTokenRemoteDataSource>()
        )
    }

    single<UserTokenLocalDataSource> { UserTokenLocalDataSourceImp(androidContext()) }
}
