package com.example.moviesapp

import android.app.Application
import com.example.moviesapp.data.di.databaseModule
import com.example.moviesapp.data.di.networkModule
import com.example.moviesapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    //val coreModules = listOf(interactionModule)
    val appModules = listOf(presentationModule)
    val dataModules = listOf(networkModule,databaseModule)

    override fun onCreate(){
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) androidLogger(Level.ERROR)
            androidContext(this@App)
            modules( appModules+dataModules)
            //modules(coreModules + dataModules + appModules)
        }

    }
}