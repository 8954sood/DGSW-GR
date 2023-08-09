package com.hu.dgswgr.remote.service

import com.hu.dgswgr.remote.request.token.TokenRequest
import com.hu.dgswgr.remote.response.Response
import com.hu.dgswgr.remote.url.DgswgrUrl
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenService {

    @POST(DgswgrUrl.Token.TOKEN)
    suspend fun token(
        @Body tokenRequest: TokenRequest
    ): Response<String>
}