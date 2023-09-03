package com.example.composesimpleapp.ui

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.composesimpleapp.ui.theme.ThemeVariants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreUtil(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("appSettings")

        val THEME_KEY = stringPreferencesKey("theme")
    }

    fun getTheme(systemTheme: String): Flow<String> = context.dataStore.data.map { preferences ->
        preferences[THEME_KEY] ?: systemTheme
    }

    suspend fun saveTheme(theme: String) {
        context.dataStore.edit {preferences ->
            preferences[THEME_KEY] = theme
        }
    }
}