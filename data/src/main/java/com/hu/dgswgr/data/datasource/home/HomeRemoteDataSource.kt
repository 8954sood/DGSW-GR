package com.hu.dgswgr.data.datasource.home

import com.hu.dgswgr.domain.model.user.UserInfo

interface HomeRemoteDataSource {

    suspend fun getInfo(): UserInfo
}