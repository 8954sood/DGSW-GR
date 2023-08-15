package com.hu.dgswgr.remote.response.lol

import com.google.gson.annotations.SerializedName
import com.hu.dgswgr.remote.response.lol.item.LolInfoItemResponse

data class LolInfoResponse(
    @field:SerializedName("class_id") val classId: Int,
    @field:SerializedName("grade") val grade: Int,
    @field:SerializedName("icon") val icon: String,
    @field:SerializedName("level") val level: Int,
    @field:SerializedName("most") val most: List<LolInfoItemResponse>,
    @field:SerializedName("kda") val kda: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("nickname") val nickname: String,
    @field:SerializedName("number") val number: Int,
    @field:SerializedName("tier_icon") val tierIcon: String,
    @field:SerializedName("tier_point") val tierPoint: String,
    @field:SerializedName("tier_str") val tierStr: String,
    @field:SerializedName("win_lose") val winLose: String,
    @field:SerializedName("win_rate") val winRate: String
)