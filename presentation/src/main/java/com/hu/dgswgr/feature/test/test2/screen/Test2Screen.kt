package com.hu.dgswgr.feature.test.test2.screen

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
fun Test2Screen(
    navController: NavController
) {
    DgswgrTheme() {
        Surface() {
            Modifier.fillMaxWidth()
        }
        Text(text = "test2")
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "돌아가기")
        }
    }
}