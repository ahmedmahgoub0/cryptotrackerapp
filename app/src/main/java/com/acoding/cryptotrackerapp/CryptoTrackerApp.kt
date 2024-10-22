package com.acoding.cryptotrackerapp

import android.app.Application
import com.acoding.cryptotrackerapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CryptoTrackerApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CryptoTrackerApp)
            modules(appModule)
        }
    }
}