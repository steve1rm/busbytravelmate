package me.androidbox.data.local.imp

import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import me.androidbox.data.local.UserTokenLocalDataSource

class UserTokenLocalDataSourceImp(private val encryptedSharedPreferences: EncryptedSharedPreferences)
    : UserTokenLocalDataSource {

    companion object {
        private const val TOKEN = "token"
    }

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
