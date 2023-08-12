package com.hu.dgswgr.remote.datasource

import com.hu.dgswgr.data.datasource.lol.LolRemoteDataSource
import com.hu.dgswgr.domain.model.lol.LolSearch
import com.hu.dgswgr.remote.mapper.toModel
import com.hu.dgswgr.remote.service.LolService
import com.hu.dgswgr.remote.utiles.dgswgrApiCall
import javax.inject.Inject

class LolRemoteDataSourceImpl @Inject constructor(
    private val lolService: LolService
): LolRemoteDataSource {
    override suspend fun search(name: String): LolSearch = dgswgrApiCall {
        lolService.search(name = name).data.toModel()
    }

}