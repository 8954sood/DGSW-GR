package com.hu.dgswgr.domain.usecase.lol

import com.hu.dgswgr.domain.repository.LolRepository
import javax.inject.Inject

class LolCreateUseCase @Inject constructor(
    private val lolRepository: LolRepository
){
    suspend operator fun invoke(param: Param ) = kotlin.runCatching {
        lolRepository.create(param.name)
    }

    data class Param(
        val name: String
    )
}