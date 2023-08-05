package com.hu.data.datasource.auth

interface AuthRemoteDataSource {

    suspend fun singUp(
        loginId: String,
        password: String,
        name: String,
        grade: String,
        classNumber: String,
        studentNumber: String,
    )

}