package com.hu.dgswgr.feature.test.test1.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.hu.dgswgr.root.navigation.NavGroup
import com.hu.dgswgr.ui.theme.DgswgrTheme

@Composable
fun Test1Screen(
    navController: NavController
) {
    Text(text = "test1")
    Button(onClick = { navController.navigate(NavGroup.Test.Test2) }) {
        Text(text = "넘어가기")
    }
}