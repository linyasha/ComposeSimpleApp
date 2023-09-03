package com.example.composesimpleapp.utils

import android.app.Activity
import android.content.res.Configuration
import com.example.composesimpleapp.ui.theme.ThemeVariants

fun Activity.getSystemTheme(): String {
    return when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> ThemeVariants.Night.value
        else -> ThemeVariants.Day.value
    }
}