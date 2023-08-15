package com.hu.dgswgr.remote.mapper

import com.hu.dgswgr.domain.model.lol.LolInfo
import com.hu.dgswgr.domain.model.lol.LolRank
import com.hu.dgswgr.domain.model.lol.LolSearch
import com.hu.dgswgr.domain.model.lol.item.LolInfoItem
import com.hu.dgswgr.remote.response.lol.LolInfoResponse
import com.hu.dgswgr.remote.response.lol.LolRankResponse
import com.hu.dgswgr.remote.response.lol.LolSearchResponse
import com.hu.dgswgr.remote.response.lol.item.LolInfoItemResponse

internal fun LolSearchResponse.toModel() =
    LolSearch(
        icon = icon,
        level = level,
        name = name
    )

@JvmName("LolRankResponse")// 시그니쳐 오류 해결
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
        tierIcon = tierIcon,
        tierStr = tierStr
    )


//@JvmName("LolInfoItemResponse")
internal fun List<LolInfoItemResponse>.toModels(): List<LolInfoItem> =
    this.map {
        it.toModelss()
    }

internal fun LolInfoItemResponse.toModelss() =
    LolInfoItem(
        icon = icon,
        kda = kda,
        winRate = winRate
    )
internal fun LolInfoResponse.toModel(): LolInfo {
    val most = most.toModels()
    return LolInfo(
        classId = classId,
        grade = grade,
        icon = icon,
        level = level,
        most = most,
        kda = kda,
        name = name,
        nickname = nickname,
        number = number,
        tierIcon = tierIcon,
        tierPoint = tierPoint,
        tierStr = tierStr,
        winLose = winLose,
        winRate = winRate,
    )
}

