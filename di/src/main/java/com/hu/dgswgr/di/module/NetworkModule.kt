package com.hu.dgswgr.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hu.dgswgr.di.qualifier.BasicOkhttpClient
import com.hu.dgswgr.di.qualifier.TokenOkhttpClient
import com.hu.dgswgr.remote.interceptor.TokenInterceptor
import com.hu.dgswgr.remote.service.AuthService
import com.hu.dgswgr.remote.service.LolService
import com.hu.dgswgr.remote.service.TokenService
import com.hu.dgswgr.remote.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.di.qualifier.BasicRetrofit
import kr.hs.dgsw.smartschool.di.qualifier.TokenRetrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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

    @BasicOkhttpClient
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okhttpBuilder = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
        return okhttpBuilder.build()
    }

    @TokenOkhttpClient
    @Provides
    @Singleton
    fun provideTokenOkHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okhttpBuilder = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(tokenInterceptor)
        return okhttpBuilder.build()
    }


    @BasicRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(@BasicOkhttpClient okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @TokenRetrofit
    @Provides
    @Singleton
    fun provideTokenRetrofit(@TokenOkhttpClient okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providesAuthService(@BasicRetrofit retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun providesTokenService(@BasicRetrofit retrofit: Retrofit): TokenService =
        retrofit.create(TokenService::class.java)

    @Singleton
    @Provides
    fun providesUserService(@TokenRetrofit retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Singleton
    @Provides
    fun providesLolService(@TokenRetrofit retrofit: Retrofit): LolService =
        retrofit.create(LolService::class.java)

}