package com.hu.dgswgr.remote.mapper

import com.hu.dgswgr.data.data.LoginData
import com.hu.dgswgr.domain.model.token.Token
import com.hu.dgswgr.remote.response.auth.LoginResponse


internal fun LoginResponse.toLoginData(): LoginData =
    LoginData(
        token = Token(token, refreshToken)
    )