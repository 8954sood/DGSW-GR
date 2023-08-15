package com.hu.dgswgr.feature.rank.choose.mvi

import com.hu.dgswgr.domain.model.lol.LolRank
import com.hu.dgswgr.domain.model.lol.item.LolInfoItem
import com.hu.dgswgr.feature.rank.mvi.RankSideEffect

data class RankChooseState(
    val classId: Int = 0,
    val grade: Int = 0,
    val icon: String = "",
    val level: Int = 0,
    val most: List<LolInfoItem> = listOf(LolInfoItem("", "", ""), LolInfoItem("", "", ""), LolInfoItem("", "", "")),
    val name: String = "",
    val nickname: String = "",
    val number: Int = 0,
    val tierIcon: String = "",
    val tierPoint: String = "",
    val tierStr: String = "",
    val winLose: String = "",
    val winRate: String = "",

    val loading: Boolean = true
)


sealed class RankChooseSideEffect {
    data class ToastError(val exception: Throwable) : RankChooseSideEffect()
}