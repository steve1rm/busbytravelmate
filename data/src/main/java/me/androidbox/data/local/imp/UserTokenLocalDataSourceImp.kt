package me.androidbox.data.local.imp

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import me.androidbox.data.local.UserTokenLocalDataSource
import org.koin.core.annotation.Single

/** Should be singleton */
class UserTokenLocalDataSourceImp(context: Context) : UserTokenLocalDataSource {

    companion object {
        private const val TOKEN = "token"
        private const val FILE_NAME = "secret_shared_prefs"
    }

    private val masterKeys = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val encryptedSharedPreferences =
        EncryptedSharedPreferences.create(
            FILE_NAME,
            masterKeys,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    override suspend fun saveUserToken(token: String) {
        encryptedSharedPreferences.edit {
            this.putString(TOKEN, token)
            this.apply()
        }
    }

    override suspend fun fetchUserToken(): String? {
        return encryptedSharedPreferences.getString(TOKEN, null)
    }
}
