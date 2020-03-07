package com.hanifkf.daggerretrofitrxjava.di

import com.google.gson.Gson
import com.hanifkf.daggerretrofitrxjava.network.ApiInterface
import com.hanifkf.daggerretrofitrxjava.repository.MainRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule{

    @Provides
    @Singleton
    fun provideLoggingInterceptor() : HttpLoggingInterceptor{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging;
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient{
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(httpLoggingInterceptor)
        return clientBuilder.build()
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
            .baseUrl("http://192.168.1.9:3030")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
    }

    @Provides
    @Singleton
    fun provideApiInterface(builder: Retrofit.Builder) : ApiInterface{
        return builder.build().create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiInterface: ApiInterface) : MainRepository{
        return  MainRepository(apiInterface)
    }

}