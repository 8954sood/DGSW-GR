package com.hu.dgswgr.feature.test.test1.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.hu.dgswgr.root.navigation.NavGroup
import com.hu.dgswgr.ui.theme.Title1

@Composable
fun Test1Screen(
    navController: NavController
) {
//    LoadInFullScreen()
    Column() {
        Title1(text = "로그인 돼 있나봄 ㄷㄷ")
    }
}
//
//@Composable
//fun DgswButton(
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier,
//    shape: Shape = MaterialTheme.shapes.medium,
//    enable: Boolean = true,
//    rippleColor: Color = Color.Unspecified,
//    rippleEnable: Boolean = true,
//    bounded: Boolean = true,
//    content: @Composable RowScope.() -> Unit,
//) {
//    val color = MaterialTheme.colors.background//backgroundColorFor(type)
//    Surface(
//        modifier = modifier,
//        shape = shape,
//        color = color,
//    ) {
//
//        Row(
//            modifier = Modifier
//                .padding(
//                    horizontal = 16.dp,
//                ),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Row(
//                modifier = Modifier
//                    .padding(vertical = 10.dp)
//            ) {
//                content()
//            }
//
//
//        }
//    }
//}

