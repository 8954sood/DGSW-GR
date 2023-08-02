package com.hu.dgswgr.root.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hu.dgswgr.feature.auth.signup.screen.SignUpScreen
import com.hu.dgswgr.feature.test.test2.screen.Test2Screen

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = NavGroup.Auth.SIGNUP) {

//        composable(NavGroup.Test.Test1) {
//            Test1Screen(navController = navController)
//        }
//
//        composable(NavGroup.Test.Test2) {
//            Test2Screen(navController = navController)
//        }


        composable(NavGroup.Auth.SIGNUP) {
            SignUpScreen(navController = navController)
        }

    }
}