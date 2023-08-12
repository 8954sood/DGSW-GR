package com.hu.dgswgr.remote.mapper

import com.hu.dgswgr.domain.model.lol.LolSearch
import com.hu.dgswgr.remote.response.lol.LolSearchResponse

internal fun LolSearchResponse.toModel() =
    LolSearch(
        icon = icon,
        level = level,
        name = name
    )