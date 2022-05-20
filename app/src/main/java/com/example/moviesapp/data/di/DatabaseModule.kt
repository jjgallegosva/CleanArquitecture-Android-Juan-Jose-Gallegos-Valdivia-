package com.example.moviesapp.data.di

import androidx.room.Room
import com.example.moviesapp.data.common.DB_NAME
import com.example.moviesapp.data.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration().build()
    }

    single { get<AppDatabase>().movieDao() }
}