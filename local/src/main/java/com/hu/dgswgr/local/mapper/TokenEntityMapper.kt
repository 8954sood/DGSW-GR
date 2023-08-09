package com.hu.dgswgr.local.mapper

import com.hu.dgswgr.domain.model.token.Token
import com.hu.dgswgr.local.entity.token.TokenEntity

internal fun TokenEntity.toModel(): Token {
    return Token(
        token = token,
        refreshToken = refreshToken
    )
}

internal fun Token.toEntity(): TokenEntity {
    return TokenEntity(
        token = token,
        refreshToken = refreshToken
    )
}