package com.hu.dgswgr.data.repository

import com.hu.dgswgr.data.BaseRepository
import com.hu.dgswgr.data.datasource.DummyCacheDataSource
import com.hu.dgswgr.data.datasource.home.HomeRemoteDataSource
import com.hu.dgswgr.domain.model.user.UserInfo
import com.hu.dgswgr.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    override val remote: HomeRemoteDataSource,
    override val cache: DummyCacheDataSource
): BaseRepository<HomeRemoteDataSource, DummyCacheDataSource> ,HomeRepository {
    override suspend fun info(): UserInfo =
        remote.getInfo()
}