package com.hu.dgswgr.data

interface BaseRepository<REMOTE, CACHE> {
    val remote: REMOTE
    val cache: CACHE
}
