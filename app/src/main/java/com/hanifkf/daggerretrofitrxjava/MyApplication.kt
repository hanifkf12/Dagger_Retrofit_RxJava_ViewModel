package com.hanifkf.daggerretrofitrxjava

import android.app.Application
import com.hanifkf.daggerretrofitrxjava.di.AppComponent
import com.hanifkf.daggerretrofitrxjava.di.DaggerAppComponent

class MyApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }

    fun getAppComponent() : AppComponent{
        return appComponent
    }
}