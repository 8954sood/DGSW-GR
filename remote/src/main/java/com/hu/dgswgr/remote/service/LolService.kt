package com.hu.dgswgr.remote.service

import com.hu.dgswgr.remote.response.Response
import com.hu.dgswgr.remote.response.lol.LolSearchResponse
import com.hu.dgswgr.remote.url.DgswgrUrl
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LolService {

    @GET(DgswgrUrl.Lol.SEARCH)
    suspend fun search(
        @Query("name") name: String
    ): Response<LolSearchResponse>
}