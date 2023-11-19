package com.example.taskhotelapp.app

import android.app.Application
import com.example.taskhotelapp.di.AppComponent
import com.example.taskhotelapp.di.DaggerAppComponent
import dagger.internal.DaggerGenerated

class App: Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}