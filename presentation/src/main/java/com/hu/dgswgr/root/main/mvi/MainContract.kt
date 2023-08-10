package com.hu.dgswgr.root.main.mvi

data class MainState(
    val check: Boolean? = null
)

sealed class MainSideEffect {
    data class ToastCheckTokenErrorMessage(val throwable: Throwable): MainSideEffect()
}
