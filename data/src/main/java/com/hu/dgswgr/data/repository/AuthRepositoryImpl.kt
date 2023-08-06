package com.hu.dgswgr.data.repository

import com.hu.dgswgr.data.BaseRepository
import com.hu.dgswgr.data.datasource.auth.AuthRemoteDataSource
import com.hu.dgswgr.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    override val remote: AuthRemoteDataSource
): BaseRepository<AuthRemoteDataSource>, AuthRepository {

    override suspend fun signUp(
        loginId: String,
        password: String,
        name: String,
        grade: String,
        classNumber: String,
        studentNumber: String,
    ) {
        remote.singUp(
            loginId =  loginId,
            password =  password,
            name =  name,
            grade =  grade,
            classNumber =  classNumber,
            studentNumber =  studentNumber
        )
    }

    override suspend fun check(loginId: String) {
        remote.check(
            loginId = loginId
        )
    }

    override suspend fun login(loginId: String, password: String) {
        remote.login(
            loginId = loginId,
            password = password
        )
    }
}