package com.hu.dgswgr.domain.repository

import com.hu.dgswgr.domain.model.token.Token

interface TokenRepository {

    suspend fun getToken(): Token

    suspend fun fetchToken(): Token

    suspend fun deleteToken()

    suspend fun checkToken(): Boolean

}