package com.example.githubclient

import android.app.Application
import com.example.githubclient.di.AppComponent
import com.example.githubclient.di.DaggerAppComponent
import com.example.githubclient.di.modules.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}