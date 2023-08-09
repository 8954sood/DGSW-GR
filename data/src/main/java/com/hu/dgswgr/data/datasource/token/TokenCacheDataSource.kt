package com.hu.dgswgr.data.datasource.token

import com.hu.dgswgr.domain.model.token.Token

interface TokenCacheDataSource {

    suspend fun getToken(): Token

    suspend fun deleteToken()

    suspend fun insertToken(token: Token)

}