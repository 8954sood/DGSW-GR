package com.hu.dgswgr.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hu.dgswgr.local.dao.TokenDao
import com.hu.dgswgr.local.entity.token.TokenEntity

@Database(
    entities = [
        TokenEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DgswgrDataBase: RoomDatabase() {
    abstract fun tokenDao(): TokenDao
}