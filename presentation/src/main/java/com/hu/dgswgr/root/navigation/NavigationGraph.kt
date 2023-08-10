package com.hu.dgswgr.root.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hu.dgswgr.feature.auth.signup.screen.SignUpScreen
import com.hu.dgswgr.feature.test.test1.screen.Test1Screen

@Composable
fun NavigationGraph(
    tokenCheck: Boolean,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = getStartDestination(tokenCheck)) {

        composable(NavGroup.Test.Test1) {
            Test1Screen(navController = navController)
        }
//
//        composable(NavGroup.Test.Test2) {
//            Test2Screen(navController = navController)
//        }


        composable(NavGroup.Auth.SIGNUP) {
            SignUpScreen(navController = navController)
        }

    }
}

private fun getStartDestination(tokenCheck: Boolean) =
    if (tokenCheck) NavGroup.Test.Test1 else NavGroup.Auth.SIGNUP
//    if (tokenCheck) NavGroup.Auth.SIGNUP else NavGroup.Auth.SIGNUP

