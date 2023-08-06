package com.hu.dgswgr.remote.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("detail")
    val message: String,
)
