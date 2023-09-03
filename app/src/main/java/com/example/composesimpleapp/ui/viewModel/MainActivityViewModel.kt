package com.example.composesimpleapp.ui.viewModel

import androidx.datastore.dataStore
import androidx.lifecycle.ViewModel
import com.example.composesimpleapp.ui.DataStoreUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MainActivityViewModel(
    private val dataStoreUtil: DataStoreUtil
): ViewModel() {

    fun getTheme(isSystemDarkTheme: String): Flow<String> = dataStoreUtil.getTheme(isSystemDarkTheme)
}