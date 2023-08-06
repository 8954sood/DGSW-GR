package com.hu.dgswgr.domain.usecase.auth

import com.hu.dgswgr.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        authRepository.login(
            loginId = param.loginId,
            password = param.password
        )
    }


    data class Param(
        val loginId: String,
        val password: String
    )
}