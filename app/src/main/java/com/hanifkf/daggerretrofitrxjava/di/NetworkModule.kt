package com.hanifkf.daggerretrofitrxjava.di

import com.google.gson.Gson
import com.hanifkf.daggerretrofitrxjava.network.ApiInterface
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule{
    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient{
        return OkHttpClient().newBuilder().build()
    }

    @Provides
    @Singleton
    fun provideGson() : Gson{
        return Gson().newBuilder().create()
    }

    @Provides
    @Singleton
    fun provideRxJava() : RxJava2CallAdapterFactory{
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(gson: Gson, okHttpClient: OkHttpClient, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory) : Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.8:3030")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
    }

    @Provides
    @Singleton
    fun provideApiInterface(builder: Retrofit.Builder) : ApiInterface{
        return builder.build().create(ApiInterface::class.java)
    }

}