package com.example.naresbob.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("naresbob_prefs")

class NaResbobPreferences(private val context: Context) {

    companion object {
        private val KEY_TOKEN = stringPreferencesKey("token")
        private val KEY_NAME = stringPreferencesKey("name")
    }

    val token: Flow<String?> = context.dataStore.data.map { pref ->
        pref[KEY_TOKEN]
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { pref ->
            pref[KEY_TOKEN] = token
        }
    }

    val name: Flow<String?> = context.dataStore.data.map { pref ->
        pref[KEY_NAME]
    }

    suspend fun saveName(name: String) {
        context.dataStore.edit { pref ->
            pref[KEY_NAME] = name
        }
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}