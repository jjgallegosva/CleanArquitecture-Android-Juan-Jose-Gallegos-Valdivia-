package com.example.moviesapp

import android.app.Application
import com.example.moviesapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    val appModules = listOf(presentationModule)

    override fun onCreate(){
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) androidLogger(Level.ERROR)
            androidContext(this@App)
            modules( appModules)
            //modules(coreModules + dataModules + appModules)
        }

    }
}