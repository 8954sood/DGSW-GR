package com.hu.dgswgr.data

interface BaseRepository<REMOTE> {
    val remote: REMOTE
//    val cache: CACHE
}
