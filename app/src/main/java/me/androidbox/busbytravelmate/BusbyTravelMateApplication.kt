package me.androidbox.busbytravelmate

import android.app.Application
import com.google.firebase.FirebaseApp
import me.androidbox.busbytravelmate.di.TestKoinViewModelModule
import me.androidbox.busbytravelmate.di.userModule
import me.androidbox.data.BuildConfig
import me.androidbox.di.RepositoryModule
import me.androidbox.di.networkModule
import me.androidbox.di.sharedPreferenceModule
import me.androidbox.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import timber.log.Timber

class BusbyTravelMateApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        FirebaseApp.initializeApp(this@BusbyTravelMateApplication)

        startKoin {
            androidLogger()
            androidContext(this@BusbyTravelMateApplication)
            modules(
                networkModule,
                RepositoryModule().module,
                useCaseModule,
                userModule,
                TestKoinViewModelModule().module,
                sharedPreferenceModule,
            )
        }
    }
}
