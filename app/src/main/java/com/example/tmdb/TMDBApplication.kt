package com.example.tmdb

import android.app.Application
import com.example.tmdb.data.AppContainer
import com.example.tmdb.data.DefaultAppContainer
import timber.log.Timber

class TMDBApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        container = DefaultAppContainer()
    }
}