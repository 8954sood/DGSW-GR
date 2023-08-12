package com.hu.dgswgr.domain.usecase.token

import com.hu.dgswgr.domain.repository.TokenRepository
import javax.inject.Inject

class DeleteTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        tokenRepository.deleteToken()
    }
}