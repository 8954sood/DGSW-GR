package com.hu.dgswgr.domain.usecase.lol

import com.hu.dgswgr.domain.repository.LolRepository
import javax.inject.Inject

class LolInfoUseCase @Inject constructor(
    private val lolRepository: LolRepository
){

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        lolRepository.info(
            id = param.id
        )
    }

    data class Param(
        val id: Int
    )

}