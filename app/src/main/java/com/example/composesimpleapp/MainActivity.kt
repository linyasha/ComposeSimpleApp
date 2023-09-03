package com.example.composesimpleapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ComposeSimpleApp
import com.example.composesimpleapp.ui.DataStoreUtil
import com.example.composesimpleapp.ui.ThemeToggle
import com.example.composesimpleapp.ui.viewModel.ThemeViewModel
import com.example.composesimpleapp.ui.theme.ComposeSimpleAppTheme
import com.example.composesimpleapp.ui.theme.ThemeVariants
import com.example.composesimpleapp.ui.viewModel.MainActivityViewModel
import com.example.composesimpleapp.utils.exceptions.UnknownThemeException
import com.example.composesimpleapp.utils.getSystemTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val themeViewModel by viewModel<ThemeViewModel>()

    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComposeSimpleApp.INSTANCE
        val systemTheme = getSystemTheme()

        setContent {
            val theme = viewModel.getTheme(systemTheme).collectAsState(initial = systemTheme)
            ComposeSimpleAppTheme(
                darkTheme = ThemeVariants.isDarkTheme(
                    theme = theme.value,
                    isSystemDark = systemTheme == ThemeVariants.Night.value
                ),
                dynamicColor = false
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(theme = theme, systemTheme = systemTheme, themeViewModel = themeViewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(theme: State<String>, systemTheme: String, themeViewModel: ThemeViewModel) {

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        val currentTheme = ThemeVariants.fromString(theme.value) ?: run {
            throw UnknownThemeException(theme.value)
        }
        val systemTheme = ThemeVariants.fromString(systemTheme) ?: run {
            throw UnknownThemeException(systemTheme)
        }
        ThemeVariants.fromString(theme.value)
        ThemeToggle(currentTheme = currentTheme, systemTheme = systemTheme) { newTheme ->
            coroutineScope.launch {
                themeViewModel.saveTheme(newTheme.value)
            }
        }
    }

}

