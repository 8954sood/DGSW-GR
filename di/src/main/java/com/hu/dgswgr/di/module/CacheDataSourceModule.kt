package com.hu.dgswgr.di.module

import com.hu.dgswgr.data.datasource.DummyCacheDataSource
import com.hu.dgswgr.data.datasource.auth.AuthCacheDataSource
import com.hu.dgswgr.data.datasource.token.TokenCacheDataSource
import com.hu.dgswgr.local.datasource.AuthCacheDataSourceImpl
import com.hu.dgswgr.local.datasource.DummyCacheDataSourceImpl
import com.hu.dgswgr.local.datasource.TokenCacheDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class CacheDataSourceModule {

    @Singleton
    @Binds
    abstract fun providesTokenCacheDataSource(
        tokenCacheDataSourceImpl: TokenCacheDataSourceImpl
    ): TokenCacheDataSource

    @Singleton
    @Binds
    abstract fun providesAuthCacheDataSource(
        authCacheDataSourceImpl: AuthCacheDataSourceImpl
    ): AuthCacheDataSource

    @Singleton
    @Binds
    abstract fun providesDummyCacheDataSource(
        dummyCacheDataSourceImpl: DummyCacheDataSourceImpl
    ): DummyCacheDataSource
}