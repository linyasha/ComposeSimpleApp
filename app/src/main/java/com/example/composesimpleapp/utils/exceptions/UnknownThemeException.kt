package com.example.composesimpleapp.utils.exceptions

import com.example.ComposeSimpleApp
import com.example.composesimpleapp.R

class UnknownThemeException(savedTheme: String?): Exception(ComposeSimpleApp.INSTANCE.getString(R.string.unknown_theme_exception, savedTheme))