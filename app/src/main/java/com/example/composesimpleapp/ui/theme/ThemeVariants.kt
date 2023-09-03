package com.example.composesimpleapp.ui.theme

sealed class ThemeVariants(val value: String) {
    object Day : ThemeVariants(DAY)
    object Night : ThemeVariants(NIGHT)
    object System : ThemeVariants(SYSTEM)

    companion object {
        const val DAY = "day"
        const val NIGHT = "night"
        const val SYSTEM = "system"

        fun isDarkTheme(theme: String, isSystemDark: Boolean): Boolean {
            return when (theme) {
                DAY -> false
                NIGHT -> true
                else -> isSystemDark
            }
        }

        fun fromString(theme: String): ThemeVariants? {
            return when (theme) {
                DAY -> Day
                NIGHT -> Night
                SYSTEM -> System
                else -> null
            }
        }
    }

}