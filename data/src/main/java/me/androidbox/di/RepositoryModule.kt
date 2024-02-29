package me.androidbox.di

import me.androidbox.data.local.UserTokenLocalDataSource
import me.androidbox.data.local.imp.UserTokenLocalDataSourceImp
import me.androidbox.data.repository.userTokenRepository.UserTokenRepositoryImp
import me.androidbox.repository.userTokenRepository.UserTokenRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<UserTokenRepository> {
        UserTokenRepositoryImp(this.get())
    }

    single<UserTokenLocalDataSource> {
        UserTokenLocalDataSourceImp(androidContext())
    }
}