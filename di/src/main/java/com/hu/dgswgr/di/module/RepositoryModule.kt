package com.hu.dgswgr.di.module

import com.hu.dgswgr.data.repository.AuthRepositoryImpl
import com.hu.dgswgr.data.repository.TokenRepositoryImpl
import com.hu.dgswgr.domain.repository.AuthRepository
import com.hu.dgswgr.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun providesAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Singleton
    @Binds
    abstract fun providesTokenRepository(
        tokenRepositoryImpl: TokenRepositoryImpl
    ): TokenRepository

}