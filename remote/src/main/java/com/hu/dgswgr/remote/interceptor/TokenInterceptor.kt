package com.hu.dgswgr.remote.interceptor

import com.hu.dgswgr.domain.exception.ExpiredRefreshTokenException
import com.hu.dgswgr.domain.usecase.token.FetchTokenUseCase
import com.hu.dgswgr.domain.usecase.token.GetTokenUseCase
import com.hu.dgswgr.remote.url.DgswgrUrl.Token.TOKEN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val fetchTokenUseCase: FetchTokenUseCase
) : Interceptor {

    private val TOKEN_ERROR = 401
    private val TOKEN_HEADER = "Authorization"

    private lateinit var token: String

    override fun intercept(chain: Interceptor.Chain): Response {

        runBlocking(Dispatchers.IO) {
            getTokenUseCase().onSuccess {
                token = it.token
            }.onFailure {
                throw ExpiredRefreshTokenException()
            }
        }

        val request: Request = chain.request().newBuilder()
            .addHeader(TOKEN_HEADER, "Dgswgr $token")
            .build()
        var response = chain.proceed(request)
        if (response.code == TOKEN_ERROR) {
            response.close()
            runBlocking {
                fetchTokenUseCase().onSuccess {
                    val refreshToken: Request = chain.request().newBuilder()
                        .addHeader(TOKEN_HEADER, "Dgswgr $token")
                        .build()
                    response = chain.proceed(refreshToken)

                    if (response.code == TOKEN_ERROR) {
                        response = Response.Builder()
                            .request(request)
                            .protocol(Protocol.HTTP_1_1)
                            .code(TOKEN_ERROR)
                            .message("세션이 만료되었습니다.")
                            .body("{\"status\":401,\"message\":\"세션이 만료되었습니다.\"}".toResponseBody())
                            .build()
                    }
                }.onFailure {
                    response = Response.Builder()
                        .request(request)
                        .protocol(Protocol.HTTP_1_1)
                        .code(TOKEN_ERROR)
                        .message("세션이 만료되었습니다.")
                        .body("{\"status\":401,\"message\":\"세션이 만료되었습니다.\"}".toResponseBody())
                        .build()
                }
            }
        }
        return response
    }
}