package com.hu.dgswgr.remote.mapper

import com.hu.dgswgr.domain.model.lol.LolRank
import com.hu.dgswgr.domain.model.lol.LolSearch
import com.hu.dgswgr.remote.response.lol.LolRankResponse
import com.hu.dgswgr.remote.response.lol.LolSearchResponse

internal fun LolSearchResponse.toModel() =
    LolSearch(
        icon = icon,
        level = level,
        name = name
    )

internal fun List<LolRankResponse>.toModel(): List<LolRank> =
    this.map {
        it.toModel()
    }

internal fun LolRankResponse.toModel() =
    LolRank(
        icon = icon,
        id = id,
        level = level,
        name = name,
        student = student,
        tier_icon = tier_icon,
        tier_str = tier_str
    )