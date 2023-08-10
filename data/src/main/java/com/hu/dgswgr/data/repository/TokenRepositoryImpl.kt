package com.hu.dgswgr.data.repository

import android.util.Log
import com.hu.dgswgr.data.BaseRepository
import com.hu.dgswgr.data.datasource.token.TokenCacheDataSource
import com.hu.dgswgr.data.datasource.token.TokenRemoteDataSource
import com.hu.dgswgr.domain.model.token.Token
import com.hu.dgswgr.domain.repository.TokenRepository
import java.lang.Exception
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    override val remote: TokenRemoteDataSource,
    override val cache: TokenCacheDataSource
): BaseRepository<TokenRemoteDataSource, TokenCacheDataSource>, TokenRepository {

    override suspend fun getToken(): Token =
        cache.getToken()

    override suspend fun fetchToken(): Token =
        cache.getToken().refreshToken.let { refreshToken ->
            remote.getToken(refreshToken).let { accessToken ->
                cache.insertToken(
                    Token(
                        token = accessToken,
                        refreshToken = refreshToken
                    )
                ).let {
                    Token(accessToken, refreshToken)
                }
            }
        }

    override suspend fun checkToken(): Boolean {
        return try {
            cache.getToken().token.let {
                Log.d("LOG", "checkToken: $it")
                Log.d("LOG", "checkToken: $it")
                Log.d("LOG", "checkToken: $it")
                Log.d("LOG", "checkToken: $it")
                Log.d("LOG", "checkToken: $it")
                Log.d("LOG", "checkToken: $it")
                Log.d("LOG", "checkToken: $it")
                Log.d("LOG", "checkToken: $it")
                it.isNotEmpty()

            }
        } catch (e: Exception) {
            false
        }
    }

}