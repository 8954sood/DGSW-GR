package com.hu.dgswgr.domain.usecase.home

import com.hu.dgswgr.domain.repository.HomeRepository
import com.hu.dgswgr.domain.repository.TokenRepository
import javax.inject.Inject

class HomeInfoUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        homeRepository.info()
    }
}