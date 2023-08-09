package com.hu.dgswgr.di.module

import com.hu.dgswgr.data.datasource.auth.AuthRemoteDataSource
import com.hu.dgswgr.data.datasource.token.TokenRemoteDataSource
import com.hu.dgswgr.remote.datasource.AuthRemoteDataSourceImpl
import com.hu.dgswgr.remote.datasource.TokenRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {


    @Singleton
    @Binds
    abstract fun providesAuthRemoteDataSource(
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource

    @Singleton
    @Binds
    abstract fun providesTokenRemoteDataSource(
        tokenRemoteDataSourceImpl: TokenRemoteDataSourceImpl
    ): TokenRemoteDataSource
}