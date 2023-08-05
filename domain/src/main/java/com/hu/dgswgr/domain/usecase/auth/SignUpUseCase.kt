package com.hu.dgswgr.domain.usecase.auth

import com.hu.dgswgr.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private var authRepository: AuthRepository
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        authRepository.signUp(
            loginId = param.loginId,
            password = param.password,
            name = param.name,
            grade = param.grade,
            classNumber = param.classNumber,
            studentNumber = param.studentNumber
        )
    }

    data class Param(
        val loginId: String,
        val password: String,
        val name: String,
        val grade: String,
        val classNumber: String,
        val studentNumber: String,
    )
}