package com.hu.dgswgr.domain.repository

interface AuthRepository {

    suspend fun signUp(
        loginId: String,
        password: String,
        name: String,
        grade: String,
        classNumber: String,
        studentNumber: String,
    )

//    suspend fun login(loginId: String, password: String, enableAutoLogin: Boolean): Token
//
//    suspend fun getIsAutoLogin(): Boolean
//
//    suspend fun logout()
}
