package com.hu.dgswgr.domain.repository

import com.hu.dgswgr.domain.model.lol.LolInfo
import com.hu.dgswgr.domain.model.lol.LolRank
import com.hu.dgswgr.domain.model.lol.LolSearch

interface LolRepository {

    suspend fun search(name: String): LolSearch

    suspend fun create(name: String)

    suspend fun rank(category: String): List<LolRank>

    suspend fun info(id: Int): LolInfo
}