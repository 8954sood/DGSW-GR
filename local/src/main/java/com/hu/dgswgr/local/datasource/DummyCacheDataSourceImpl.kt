package com.hu.dgswgr.local.datasource

import android.util.Log
import com.hu.dgswgr.data.datasource.DummyCacheDataSource
import javax.inject.Inject

class DummyCacheDataSourceImpl @Inject constructor(

): DummyCacheDataSource {
    override suspend fun dummy() {
        Log.d("TAG", "dummy: called")
    }
}