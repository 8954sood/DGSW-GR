package com.hu.dgswgr.feature.home.mvi

import com.hu.dgswgr.utiles.DgswgrString

data class HomeState(
    val name: String = "",
    val grade: String = "",
    val classNumber: String = "",
    val studentNumber: String = "",

    val nickname: String = "",
    val imageUrl: String = "${DgswgrString.URL}/api/file/static/image/None.png",
    val lolName: String = "",
    val level: Int? = null,
    val btnEnabled: Boolean = false,

    val loading: Boolean = false
)


sealed class HomeSideEffect {
    data class ToastError(val exception: Throwable) : HomeSideEffect()
}