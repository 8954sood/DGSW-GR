package com.hu.dgswgr.utiles

import android.app.Application
import com.hu.data.service.PreferenceManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DgswGRApplication: Application() {
    companion object {
        lateinit var prefs: PreferenceManager
    }

    override fun onCreate() {
        super.onCreate()
        prefs = PreferenceManager(applicationContext)
    }
}