package com.hu.dgswgr.feature.auth.signup.mvi

data class SignUpState(
    val loginId: String = "",
    val password: String = "",
    val name: String = "",
    val grade: String = "",
    val classNumber: String = "",
    val studentNumber: String = "",

    val saveLogin: Boolean = false,

    val page: Int = 0,
    val loading: Boolean = false

)

sealed class SignUpSideEffect {
    object SuccessSignUp : SignUpSideEffect()
    object SuccessCheck: SignUpSideEffect()
    object SuccessLogin: SignUpSideEffect()
    data class FailSignUp(val throwable: Throwable): SignUpSideEffect()
    data class FailCheck(val throwable: Throwable): SignUpSideEffect()
    data class FailLogin(val throwable: Throwable): SignUpSideEffect()
}