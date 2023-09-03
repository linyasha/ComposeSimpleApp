package com.example.composesimpleapp.ui.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.composesimpleapp.ui.DataStoreUtil
import com.example.composesimpleapp.ui.theme.ThemeVariants
import kotlinx.coroutines.flow.Flow

class ThemeViewModel(
    private val dataStoreUtil: DataStoreUtil
): ViewModel() {
    var currentTheme = mutableStateOf(ThemeVariants.System.value)
        private set

    fun setTheme(theme: ThemeVariants) {
        currentTheme.value = theme.value
    }

    fun getThemeFromPrefs(systemTheme: String): Flow<String> {
        return dataStoreUtil.getTheme(systemTheme)
    }

    suspend fun saveTheme(theme: String) {
        dataStoreUtil.saveTheme(theme)
    }
}