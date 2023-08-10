package com.hu.dgswgr.local.datasource

import com.hu.dgswgr.data.datasource.token.TokenCacheDataSource
import com.hu.dgswgr.domain.model.token.Token
import com.hu.dgswgr.local.dao.TokenDao
import com.hu.dgswgr.local.entity.token.TokenEntity
import com.hu.dgswgr.local.mapper.toEntity
import com.hu.dgswgr.local.mapper.toModel
import javax.inject.Inject

class TokenCacheDataSourceImpl @Inject constructor(
    private val tokenDao: TokenDao
): TokenCacheDataSource {

    override suspend fun getToken(): Token =
        tokenDao.getToken().toModel()

    override suspend fun deleteToken() =
        tokenDao.deleteToken()

    override suspend fun insertToken(token: Token) =
        tokenDao.insert(token.toEntity())

}
