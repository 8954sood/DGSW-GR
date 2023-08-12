package com.hu.dgswgr.root.navigation

import com.hu.dgswgr.R

sealed class BottomNavItem(
    val title: Int, val icon: Int, val screenRoute: String
) {
    object Rank : BottomNavItem(R.string.Rank, R.drawable.rank, NavGroup.Test.Test2)
    object User : BottomNavItem(R.string.User, R.drawable.user, NavGroup.Home.HOME)
}