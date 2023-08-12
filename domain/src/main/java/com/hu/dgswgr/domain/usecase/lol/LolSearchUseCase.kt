package com.hu.dgswgr.domain.usecase.lol

import com.hu.dgswgr.domain.repository.LolRepository
import javax.inject.Inject

class LolSearchUseCase @Inject constructor(
    private val lolRepository: LolRepository
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        lolRepository.search(
            name = param.name
        )
    }

    data class Param(
        val name: String
    )

}