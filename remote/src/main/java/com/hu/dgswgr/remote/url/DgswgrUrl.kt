package com.hu.dgswgr.remote.url

object DgswgrUrl {
    private const val API = "/api"

    object Auth {
        private const val AUTH = "${API}/user"
        const val SIGNUP = "${AUTH}/create"
        const val CHECK = "${AUTH}/check"
        const val LOGIN = "${AUTH}/login"
    }
}