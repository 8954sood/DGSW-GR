package com.hu.dgswgr.feature.auth.signup.mvi

data class SignUpState(
    val loginId: String = "",
    val password: String = "",
    val name: String = "",
    val grade: String = "",
    val classNumber: String = "",
    val studentNumber: String = "",

    val page: Int = 0,
    val loading: Boolean = false

)

sealed class SignUpSideEffect {
    object SuccessSignUp : SignUpSideEffect()
    data class FailSignUp(val throwable: Throwable): SignUpSideEffect()
}