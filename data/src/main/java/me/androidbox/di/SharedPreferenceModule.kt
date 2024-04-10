package me.androidbox.di

import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/*
@Module
@ComponentScan
class SharedPreferenceModule {

    companion object {
        private const val FILE_NAME = "secret_shared_prefs"
    }

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
    }

    @Single
    fun provideContext(androidContext: AndroidContext): Context {
        return androidContext.get()
    }
}
*/


val sharedPreferenceModule = module {
    single {
        val masterKeys = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val FILE_NAME = "secret_shared_prefs"

        EncryptedSharedPreferences.create(
            FILE_NAME,
            masterKeys,
            androidContext(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}

