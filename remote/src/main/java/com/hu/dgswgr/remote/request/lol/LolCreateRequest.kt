package com.hu.dgswgr.remote.request.lol

import com.google.gson.annotations.SerializedName

data class LolCreateRequest(
    @field:SerializedName("name") val name: String
)