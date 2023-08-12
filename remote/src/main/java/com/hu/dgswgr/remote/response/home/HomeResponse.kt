package com.hu.dgswgr.remote.response.home

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("grade") val grade: String,
    @field:SerializedName("class_id") val classNumber: String,
    @field:SerializedName("number") val studentNumber: String
)