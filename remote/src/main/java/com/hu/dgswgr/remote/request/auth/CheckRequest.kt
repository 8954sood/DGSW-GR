package com.hu.dgswgr.remote.request.auth

import com.google.gson.annotations.SerializedName

data class CheckRequest (
    @field:SerializedName("login_id") val loginId: String
)