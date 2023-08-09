package com.hu.dgswgr.remote.datasource

import com.hu.dgswgr.data.datasource.token.TokenRemoteDataSource
import com.hu.dgswgr.remote.request.token.TokenRequest
import com.hu.dgswgr.remote.service.TokenService
import com.hu.dgswgr.remote.utiles.dgswgrApiCall
import javax.inject.Inject

class TokenRemoteDataSourceImpl @Inject constructor(
    private val tokenService: TokenService
): TokenRemoteDataSource {

    override suspend fun getToken(token: String): String = dgswgrApiCall {
        tokenService.token(
            TokenRequest(
                refreshToken = token
            )
        ).data
    }

}