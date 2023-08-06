package com.hu.dgswgr.remote.response.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("refresh_token") val refreshToken: String,
    @field:SerializedName("access_token") val token: String,
)
