package com.hu.dgswgr.data.repository

import com.hu.dgswgr.data.BaseRepository
import com.hu.dgswgr.data.datasource.token.TokenCacheDataSource
import com.hu.dgswgr.data.datasource.token.TokenRemoteDataSource
import com.hu.dgswgr.domain.model.token.Token
import com.hu.dgswgr.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    override val remote: TokenRemoteDataSource,
    override val cache: TokenCacheDataSource
): BaseRepository<TokenRemoteDataSource, TokenCacheDataSource>, TokenRepository {

    override suspend fun getToken(): Token =
        cache.getToken()



}