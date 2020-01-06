package com.hanifkf.daggerretrofitrxjava.di

import com.hanifkf.daggerretrofitrxjava.MainActivity
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent{
    fun inject(mainActivity: MainActivity)
}