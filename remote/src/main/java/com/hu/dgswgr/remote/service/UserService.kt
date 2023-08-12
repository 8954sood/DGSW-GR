package com.hu.dgswgr.remote.service

import com.hu.dgswgr.remote.response.Response
import com.hu.dgswgr.remote.response.home.HomeResponse
import com.hu.dgswgr.remote.url.DgswgrUrl
import retrofit2.http.GET

interface UserService {

    @GET(DgswgrUrl.Home.INFO)
    suspend fun homeInfo(

    ): Response<HomeResponse>
}