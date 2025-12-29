package com.kartikay.data.dataStore


import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserData(context: Context) {

    companion object {
        private val NAME_KEY = stringPreferencesKey("user_name")
        private val EMAIL_KEY = stringPreferencesKey("user_email")

        // Extension function on Context for DataStore
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")
    }

    private val dataStore = context.dataStore

    suspend fun saveUser(name: String, email: String) {
        dataStore.edit { prefs: MutablePreferences ->
            prefs[NAME_KEY] = name
            prefs[EMAIL_KEY] = email
        }
    }

    val userName: Flow<String> = dataStore.data.map { prefs: Preferences ->
        prefs[NAME_KEY] ?: ""
    }

    val userEmail: Flow<String> = dataStore.data.map { prefs: Preferences ->
        prefs[EMAIL_KEY] ?: ""
    }
}
