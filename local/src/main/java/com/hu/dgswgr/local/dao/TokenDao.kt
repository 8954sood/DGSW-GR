package com.hu.dgswgr.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.hu.dgswgr.local.base.BaseDao
import com.hu.dgswgr.local.entity.token.TokenEntity
import com.hu.dgswgr.local.table.DgswgrTable

@Dao
interface TokenDao : BaseDao<TokenEntity> {

    @Query("SELECT * FROM ${DgswgrTable.TOKEN} WHERE idx = 0")
    suspend fun getToken(): TokenEntity

    @Query("DELETE FROM ${DgswgrTable.TOKEN}")
    suspend fun deleteToken()
}
