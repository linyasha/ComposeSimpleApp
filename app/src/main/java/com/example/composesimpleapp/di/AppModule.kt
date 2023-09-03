package com.example.composesimpleapp.di

import com.example.composesimpleapp.ui.DataStoreUtil
import com.example.composesimpleapp.ui.viewModel.MainActivityViewModel
import com.example.composesimpleapp.ui.viewModel.ThemeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    factory {
        DataStoreUtil(androidContext())
    }

    viewModel<ThemeViewModel> {
        ThemeViewModel(
            dataStoreUtil = get()
        )
    }

    viewModel<MainActivityViewModel> {
        MainActivityViewModel(
            dataStoreUtil = get()
        )
    }

}