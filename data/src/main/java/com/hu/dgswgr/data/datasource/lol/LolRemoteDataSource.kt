package com.hu.dgswgr.data.datasource.lol

import com.hu.dgswgr.domain.model.lol.LolInfo
import com.hu.dgswgr.domain.model.lol.LolRank
import com.hu.dgswgr.domain.model.lol.LolSearch

interface LolRemoteDataSource {

    suspend fun search(name: String): LolSearch

    suspend fun create(name: String)

    suspend fun rank(category: String): List<LolRank>

    suspend fun info(id: Int): LolInfo

}