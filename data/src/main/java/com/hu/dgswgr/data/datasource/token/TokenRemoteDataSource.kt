package com.hu.dgswgr.data.datasource.token

import com.hu.dgswgr.domain.model.token.Token

interface TokenRemoteDataSource {

    suspend fun getToken(token: String): String
}