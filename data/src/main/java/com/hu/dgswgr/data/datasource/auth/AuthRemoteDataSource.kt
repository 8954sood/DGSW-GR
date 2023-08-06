package com.hu.dgswgr.data.datasource.auth

import com.hu.dgswgr.data.data.LoginData

interface AuthRemoteDataSource {

    suspend fun singUp(
        loginId: String,
        password: String,
        name: String,
        grade: String,
        classNumber: String,
        studentNumber: String,
    )

    suspend fun check(
        loginId: String
    )

    suspend fun login(
        loginId: String,
        password: String
    ): LoginData

}