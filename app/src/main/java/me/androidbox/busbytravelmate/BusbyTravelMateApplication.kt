package me.androidbox.busbytravelmate

import android.app.Application
import me.androidbox.data.BuildConfig
import me.androidbox.di.networkModule
import me.androidbox.di.repositoryModule
import me.androidbox.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class BusbyTravelMateApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@BusbyTravelMateApplication)
            modules(
                networkModule,
                repositoryModule,
                useCaseModule
            )
        }
    }
}