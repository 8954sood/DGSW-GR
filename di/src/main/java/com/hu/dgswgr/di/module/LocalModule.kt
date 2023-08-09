package com.hu.dgswgr.di.module

import android.content.Context
import androidx.room.Room
import com.hu.dgswgr.local.dao.TokenDao
import com.hu.dgswgr.local.database.DgswgrDataBase
import com.hu.dgswgr.local.table.DgswgrTable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideDgswgrDataBase(
        @ApplicationContext context: Context
    ): DgswgrDataBase = Room
        .databaseBuilder(
            context,
            DgswgrDataBase::class.java,
            DgswgrTable.DATABASE
        )
        .build()

    @Provides
    @Singleton
    fun provideTokenDao(
        dgswgrDataBase: DgswgrDataBase
    ): TokenDao = dgswgrDataBase.tokenDao()
}