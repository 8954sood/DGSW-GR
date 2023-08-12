package com.hu.dgswgr.domain.repository

import com.hu.dgswgr.domain.model.lol.LolSearch

interface LolRepository {

    suspend fun search(name: String): LolSearch
}