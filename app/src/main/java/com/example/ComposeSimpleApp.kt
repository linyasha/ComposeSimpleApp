package com.example

import android.app.Application
import com.example.composesimpleapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ComposeSimpleApp: Application() {

    companion object {
        lateinit var INSTANCE: ComposeSimpleApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@ComposeSimpleApp)
            androidLogger(Level.DEBUG)
            modules(appModule)
        }
    }

}