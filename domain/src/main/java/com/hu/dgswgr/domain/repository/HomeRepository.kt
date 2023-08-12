package com.hu.dgswgr.domain.repository

import com.hu.dgswgr.domain.model.user.UserInfo

interface HomeRepository {

    suspend fun info(): UserInfo
}