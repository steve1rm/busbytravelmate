package me.androidbox.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.androidbox.data.BuildConfig
import me.androidbox.data.remote.service.imp.UserTokenRemoteDataSourceImp
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> { _ ->
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                })
            }

            install(Logging) {
                if (BuildConfig.DEBUG) {
                    this.level = LogLevel.BODY
                } else {
                    this.level = LogLevel.NONE
                }
            }
        }
    }

    factory<me.androidbox.data.remote.service.UserTokenRemoteDataSource> {
        UserTokenRemoteDataSourceImp(this.get<HttpClient>())
    }
}