package com.hu.dgswgr.remote.response.lol

import com.google.gson.annotations.SerializedName

data class LolSearchResponse(
    @field:SerializedName("icon") val icon: String,
    @field:SerializedName("level") val level: Int,
    @field:SerializedName("name") val name: String
)