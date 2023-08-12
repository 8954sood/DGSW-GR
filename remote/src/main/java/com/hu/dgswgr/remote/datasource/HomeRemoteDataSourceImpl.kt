package com.hu.dgswgr.remote.datasource

import com.hu.dgswgr.data.datasource.home.HomeRemoteDataSource
import com.hu.dgswgr.domain.model.user.UserInfo
import com.hu.dgswgr.remote.mapper.toModel
import com.hu.dgswgr.remote.service.UserService
import com.hu.dgswgr.remote.utiles.dgswgrApiCall
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService
): HomeRemoteDataSource {
    override suspend fun getInfo(): UserInfo =  dgswgrApiCall {
        userService.homeInfo().data.toModel()
    }
}