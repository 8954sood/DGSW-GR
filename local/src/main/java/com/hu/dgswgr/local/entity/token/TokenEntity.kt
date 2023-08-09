package com.hu.dgswgr.local.entity.token

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hu.dgswgr.local.table.DgswgrTable

@Entity(
    tableName = DgswgrTable.TOKEN
)
data class TokenEntity(
    @PrimaryKey val idx: Int,
    val token: String,
    val refreshToken: String
) {
    constructor(token: String, refreshToken: String) : this(0, token, refreshToken)
}