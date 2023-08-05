package com.hu.data

interface BaseRepository<REMOTE> {
    val remote: REMOTE
//    val cache: CACHE
}
