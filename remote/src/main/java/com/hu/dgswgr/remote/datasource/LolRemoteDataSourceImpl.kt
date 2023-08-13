package com.hu.dgswgr.remote.datasource

import com.hu.dgswgr.data.datasource.lol.LolRemoteDataSource
import com.hu.dgswgr.domain.model.lol.LolRank
import com.hu.dgswgr.domain.model.lol.LolSearch
import com.hu.dgswgr.remote.mapper.toModel
import com.hu.dgswgr.remote.request.lol.LolCreateRequest
import com.hu.dgswgr.remote.service.LolService
import com.hu.dgswgr.remote.utiles.dgswgrApiCall
import javax.inject.Inject

class LolRemoteDataSourceImpl @Inject constructor(
    private val lolService: LolService
): LolRemoteDataSource {
    override suspend fun search(name: String): LolSearch = dgswgrApiCall {
        lolService.search(name = name).data.toModel()
    }

    override suspend fun create(name: String): Unit = dgswgrApiCall {
        lolService.create(
            LolCreateRequest(name)
        )
    }

    override suspend fun rank(category: String): List<LolRank> = dgswgrApiCall {
        lolService.rank(category = category).data.toModel()
    }

}