package com.hu.dgswgr.remote.request.auth

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @field:SerializedName("login_id") val loginId: String,
    @field:SerializedName("password") val password: String
)