package com.hu.dgswgr.root.navigation

sealed class NavGroup(group: String) {
    object Test: NavGroup("test") {
        const val Test1 = "test1"
        const val Test2 = "test2"
    }

    object Auth: NavGroup("auth") {
        const val SIGNUP = "signup"
    }

    object Home: NavGroup("home") {
        const val HOME = "home"
    }

    object Rank: NavGroup("rank") {
        const val RANK = "rank"
        const val CHOOSE = "choose/{id}"
    }
}