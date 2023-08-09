package com.hu.dgswgr.remote.request.token

import com.google.gson.annotations.SerializedName

data class TokenRequest(
    @field:SerializedName("refresh_token") val refreshToken: String
)