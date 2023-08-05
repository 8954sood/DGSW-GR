package com.hu.data.repository

import com.hu.data.BaseRepository
import com.hu.data.datasource.auth.AuthRemoteDataSource
import com.hu.domain.repository.AuthRepository
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
}