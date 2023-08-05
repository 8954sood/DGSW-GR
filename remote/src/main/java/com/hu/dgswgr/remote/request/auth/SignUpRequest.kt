package com.hu.dgswgr.remote.request.auth

import com.google.gson.annotations.SerializedName

data class SignUpRequest (
    @field:SerializedName("loginId") val loginId: String,
    @field:SerializedName("password") val password: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("grade") val grade: String,
    @field:SerializedName("class") val classNumber: String,
    @field:SerializedName("number") val studentNumber: String,
)