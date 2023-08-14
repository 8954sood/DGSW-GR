package com.hu.dgswgr.feature.rank.mvi

import com.hu.dgswgr.domain.model.lol.LolRank
import com.hu.dgswgr.feature.home.mvi.HomeSideEffect
import com.hu.dgswgr.utiles.DgswgrString

data class RankState(
    val list: List<LolRank> = listOf(LolRank(null, 0, 0, "불러오지 못했습니다.", "", null, "")),

    val firstTime: Boolean = true,
    val loading: Boolean = false
)


sealed class RankSideEffect {
    data class ToastError(val exception: Throwable) : RankSideEffect()
}