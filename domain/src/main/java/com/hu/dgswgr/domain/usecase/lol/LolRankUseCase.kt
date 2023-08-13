package com.hu.dgswgr.domain.usecase.lol

import com.hu.dgswgr.domain.repository.LolRepository
import javax.inject.Inject

class LolRankUseCase @Inject constructor(
    private val lolRepository: LolRepository
) {
    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        lolRepository.rank(param.category)
    }

    data class Param(
        val category: String
    )
}