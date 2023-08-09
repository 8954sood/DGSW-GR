package com.hu.dgswgr.local.datasource

import com.hu.dgswgr.data.datasource.auth.AuthCacheDataSource
import com.hu.dgswgr.domain.model.token.Token
import com.hu.dgswgr.local.dao.TokenDao
import com.hu.dgswgr.local.mapper.toEntity
import javax.inject.Inject

class AuthCacheDataSourceImpl @Inject constructor(
   private val tokenDao: TokenDao
): AuthCacheDataSource {
    override suspend fun insertToken(token: Token) {
        tokenDao.insert(token.toEntity())
    }
}