package com.hu.dgswgr.domain.model.lol

import com.google.gson.annotations.SerializedName
import com.hu.dgswgr.domain.model.lol.item.LolInfoItem

data class LolInfo(
    val classId: Int,
    val grade: Int,
    val icon: String,
    val level: Int,
    val most: List<LolInfoItem>,
    val kda: String,
    val name: String,
    val nickname: String,
    val number: Int,
    val tierIcon: String,
    val tierPoint: String,
    val tierStr: String,
    val winLose: String,
    val winRate: String
)