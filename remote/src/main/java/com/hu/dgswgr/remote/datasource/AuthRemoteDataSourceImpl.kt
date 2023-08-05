package com.hu.dgswgr.remote.datasource

import com.hu.data.datasource.auth.AuthRemoteDataSource
import com.hu.dgswgr.remote.request.auth.SignUpRequest
import com.hu.dgswgr.remote.service.AuthService
import com.hu.dgswgr.remote.utiles.dgswgrApiCall
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
): AuthRemoteDataSource {
    override suspend fun singUp(
        loginId: String,
        password: String,
        name: String,
        grade: String,
        classNumber: String,
        studentNumber: String,
    ) = dgswgrApiCall {
        authService.signUp(
            SignUpRequest(loginId, password, name, grade, classNumber, studentNumber)
        ).data
    }


}