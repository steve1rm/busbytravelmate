package me.androidbox.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import me.androidbox.data.remote.service.UserLoginRegisterRemoteDataSource
import me.androidbox.data.remote.service.imp.UserLoginRegisterRemoteDataSourceImp
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class RepositoryModule {

    companion object {
        private const val FILE_NAME = "secret_shared_prefs"
    }

    @Single
    fun provideUserLoginRegisterRemoteDataSourceImp(firebaseAuth: FirebaseAuth): UserLoginRegisterRemoteDataSource {
        return UserLoginRegisterRemoteDataSourceImp(firebaseAuth = firebaseAuth)
    }

    @Single
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }
}

/*
    @Single
    fun provideSharedPreferences(context: Context): SharedPreferences {
        val masterKeys = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        return EncryptedSharedPreferences.create(
            FILE_NAME,
            masterKeys,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }*/

/*

val repositoryModule = module {
    single<UserValidationRepository> {
        UserValidationRepositoryImp(
            this.get<UserTokenLocalDataSource>(),
            this.get<UserTokenRemoteDataSource>(),
            this.get<UserLoginRegisterRemoteDataSource>()
        )
    }

    single<UserTokenLocalDataSource> {
        UserTokenLocalDataSourceImp(get<EncryptedSharedPreferences>())
    }

    single<UserLoginRegisterRemoteDataSource> {
        UserLoginRegisterRemoteDataSourceImp(this.get<FirebaseAuth>())
    }
}*/


