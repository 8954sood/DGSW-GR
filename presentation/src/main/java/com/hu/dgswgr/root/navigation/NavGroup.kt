package com.hu.dgswgr.root.navigation

sealed class NavGroup(group: String) {
    object Test: NavGroup("test") {
        const val Test1 = "test1"
        const val Test2 = "test2"
    }
}