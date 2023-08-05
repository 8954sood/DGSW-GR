package com.hu.dgswgr.remote.service

import com.hu.dgswgr.remote.request.auth.SignUpRequest
import com.hu.dgswgr.remote.response.Response
import com.hu.dgswgr.remote.url.DgswgrUrl
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST(DgswgrUrl.Auth.SIGNUP)
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest,
    ): Response<Unit>

//    @POST(DodamUrl.Auth.LOGIN)
//    suspend fun login(
//        @Body loginRequest: LoginRequest,
//    ): Response<LoginResponse>
}
