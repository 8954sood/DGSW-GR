package com.hu.dgswgr.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hu.dgswgr.remote.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okhttpBuilder = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
//            .addInterceptor(tokenInterceptor)
        return okhttpBuilder.build()
    }

//    @Provides
//    @Singleton
//    fun provideOkHttpClient(): OkHttpClient {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        val okhttpBuilder = OkHttpClient().newBuilder()
//            .addInterceptor(interceptor)
////            .addInterceptor(tokenInterceptor)
//        return okhttpBuilder.build()
//    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providesAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

}