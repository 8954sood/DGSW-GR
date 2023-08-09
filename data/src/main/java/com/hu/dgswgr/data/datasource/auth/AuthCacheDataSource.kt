package com.hu.dgswgr.data.datasource.auth

import com.hu.dgswgr.domain.model.token.Token

interface AuthCacheDataSource {

    suspend fun insertToken(token: Token)
}