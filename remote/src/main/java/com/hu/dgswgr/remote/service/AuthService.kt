package com.hu.dgswgr.remote.service

import com.hu.dgswgr.remote.request.auth.CheckRequest
import com.hu.dgswgr.remote.request.auth.LoginRequest
import com.hu.dgswgr.remote.request.auth.SignUpRequest
import com.hu.dgswgr.remote.response.Response
import com.hu.dgswgr.remote.response.auth.LoginResponse
import com.hu.dgswgr.remote.url.DgswgrUrl
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST(DgswgrUrl.Auth.SIGNUP)
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest,
    ): Response<Unit>

    @POST(DgswgrUrl.Auth.CHECK)
    suspend fun check(
        @Body checkRequest: CheckRequest,
    ): Response<Unit>

    @POST(DgswgrUrl.Auth.LOGIN)
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): Response<LoginResponse>
//    @POST(DodamUrl.Auth.LOGIN)
//    suspend fun login(
//        @Body loginRequest: LoginRequest,
//    ): Response<LoginResponse>
}
