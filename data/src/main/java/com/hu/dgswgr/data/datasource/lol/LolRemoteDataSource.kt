package com.hu.dgswgr.data.datasource.lol

import com.hu.dgswgr.domain.model.lol.LolSearch

interface LolRemoteDataSource {

    suspend fun search(name: String): LolSearch

}