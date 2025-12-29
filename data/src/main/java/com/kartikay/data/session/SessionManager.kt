package com.kartikay.data.session

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys


class SessionManager(val context: Context) {
    companion object {
        private const val TOKEN_FILE = "token_file"
    }

    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    val sharedPreferences = EncryptedSharedPreferences.create(
        TOKEN_FILE,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun addAuthToken(token: String?){
        sharedPreferences.edit().putString("authToken",token).apply()
    }
    fun getAuthToken():String?{
        return sharedPreferences.getString("authToken",null)
    }
}