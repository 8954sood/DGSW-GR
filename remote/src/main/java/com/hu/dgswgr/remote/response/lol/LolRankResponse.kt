package com.hu.dgswgr.remote.response.lol

import com.google.gson.annotations.SerializedName

data class LolRankResponse(
    @field:SerializedName("icon") val icon: String,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("level") val level: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("student") val student: String,
    @field:SerializedName("tier_icon") val tier_icon: String,
    @field:SerializedName("tier_str") val tier_str: String
)