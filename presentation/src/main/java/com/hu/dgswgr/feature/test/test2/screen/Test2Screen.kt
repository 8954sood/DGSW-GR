package com.hu.dgswgr.feature.test.test2.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun Test2Screen(
    navController: NavController
) {
    Surface() {
        Modifier.fillMaxSize()
    }
    Text(text = "test2")
    Button(onClick = { navController.popBackStack() }) {
        Text(text = "돌아가기")
    }

}