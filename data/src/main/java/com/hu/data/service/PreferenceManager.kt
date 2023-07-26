package com.hu.data.service

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(DGSWGR_APP, Context.MODE_PRIVATE)

    companion object {
        const val DGSWGR_APP = "DGSWGR_APP"
    }
}