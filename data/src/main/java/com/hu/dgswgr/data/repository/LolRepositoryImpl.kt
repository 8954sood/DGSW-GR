package com.hu.dgswgr.data.repository

import com.hu.dgswgr.data.BaseRepository
import com.hu.dgswgr.data.datasource.DummyCacheDataSource
import com.hu.dgswgr.data.datasource.lol.LolRemoteDataSource
import com.hu.dgswgr.domain.model.lol.LolRank
import com.hu.dgswgr.domain.model.lol.LolSearch
import com.hu.dgswgr.domain.repository.LolRepository
import javax.inject.Inject

class LolRepositoryImpl @Inject constructor(
    override val remote: LolRemoteDataSource,
    override val cache: DummyCacheDataSource
): BaseRepository<LolRemoteDataSource, DummyCacheDataSource>, LolRepository {
    override suspend fun search(name: String): LolSearch =
        remote.search(
            name = name
        )

    override suspend fun create(name: String) {
        remote.create(
            name = name
        )
    }

    override suspend fun rank(category: String): List<LolRank> =
        remote.rank(category)
}