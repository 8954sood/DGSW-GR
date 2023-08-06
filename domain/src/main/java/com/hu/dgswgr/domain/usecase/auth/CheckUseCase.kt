package com.hu.dgswgr.domain.usecase.auth

import com.hu.dgswgr.domain.repository.AuthRepository
import javax.inject.Inject

class CheckUseCase @Inject constructor(
    private var authRepository: AuthRepository
) {
    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        authRepository.check(
            loginId = param.loginId
        )
    }

    data class Param(
        val loginId: String
    )
}