package com.hu.dgswgr.remote.service

import com.hu.dgswgr.remote.request.lol.LolCreateRequest
import com.hu.dgswgr.remote.response.Response
import com.hu.dgswgr.remote.response.lol.LolRankResponse
import com.hu.dgswgr.remote.response.lol.LolSearchResponse
import com.hu.dgswgr.remote.url.DgswgrUrl
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LolService {

    @GET(DgswgrUrl.Lol.SEARCH)
    suspend fun search(
        @Query("name") name: String
    ): Response<LolSearchResponse>

    @POST(DgswgrUrl.Lol.CREATE)
    suspend fun create(
        @Body lolCreateRequest: LolCreateRequest
    ): Response<String>

    @GET(DgswgrUrl.Lol.RANK)
    suspend fun rank(
        @Query("category") category: String
    ): Response<List<LolRankResponse>>
}