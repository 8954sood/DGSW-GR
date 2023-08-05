package com.hu.dgswgr.di

import com.hu.dgswgr.data.repository.AuthRepositoryImpl
import com.hu.dgswgr.domain.repository.AuthRepository
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
}