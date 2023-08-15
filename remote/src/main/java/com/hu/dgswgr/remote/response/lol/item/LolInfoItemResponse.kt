package com.hu.dgswgr.remote.response.lol.item

import com.google.gson.annotations.SerializedName

data class LolInfoItemResponse(
    @field:SerializedName("icon") val icon: String,
    @field:SerializedName("kda") val kda: String,
    @field:SerializedName("win_rate") val winRate: String
)