package com.hanifkf.daggerretrofitrxjava.di

import android.content.Context
import com.hanifkf.daggerretrofitrxjava.MainActivity
import com.hanifkf.daggerretrofitrxjava.viewmodel.CreatePersonActivity
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent{
    fun inject(mainActivity: MainActivity)
    fun inject(createPersonActivity: CreatePersonActivity)
}