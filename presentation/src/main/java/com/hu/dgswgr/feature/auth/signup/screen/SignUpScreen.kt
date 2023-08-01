package com.hu.dgswgr.feature.auth.signup.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hu.dgswgr.feature.auth.signup.vm.SignUpViewModel
import com.hu.dgswgr.ui.components.appbar.DgswAppBar
import com.hu.dgswgr.ui.components.textfiled.DgswgrLongTextField
import com.hu.dgswgr.ui.theme.DgswgrTheme
import com.hu.dgswgr.ui.theme.Title1
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SignUpScreen(
    navController: NavController,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    val signUpSate = signUpViewModel.collectAsState().value


    Column() {
        Text(text = "test2")


    }
    Text(text = "test2")
    Button(onClick = { navController.popBackStack() }) {
        Text(text = "돌아가기")
    }


}

@OptIn(ExperimentalComposeUiApi::class)
@Preview(showSystemUi = true)
@Composable
fun PreviewScreen1(
) {
    val focus = LocalFocusManager.current
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                focus.clearFocus()

            })
        }
    )
    {
        var value by remember { mutableStateOf("") }
        DgswAppBar(
            text = "회원가입",
            onClick = { Log.d("dd ", "")}
        )
        Row(modifier = Modifier
            .padding(16.dp, 0.dp)) {
//            Spacer(modifier = Modifier.width(16.dp))
            Column() {
                Spacer(modifier = Modifier.height(32.dp))
                Title1(text = "여러분의 게임랭크를")
                Spacer(modifier = Modifier.height(2.dp))
                Title1(text = "자랑해볼까요?")
                DgswgrLongTextField(
                    value =value,
                    decorationBox = Modifier
                        .fillMaxWidth()
                        .background(DgswgrTheme.color.Black40)
                        .height((0.5).dp)
                ) {
                    value = it
                }
            }
        }

    }
}

//TextField(
//value = value.value,
//onValueChange = { value.value = it },
//placeholder = { Text(text = "준식아 입력해라", color = DgswgrTheme.color.Black20) },
//colors = TextFieldDefaults.textFieldColors(
//backgroundColor = DgswgrTheme.color.White,
//focusedIndicatorColor = DgswgrTheme.color.Black60,
//unfocusedIndicatorColor = DgswgrTheme.color.Black20
//))


// 회원가입 선 만드는거
//modifier = Modifier.drawWithContent {
//    drawContent()
//    drawLine(
//        color = Color.Black,
//        start = Offset(
//            x = 0f,
//            y = size.height - 1.dp.toPx(),
//        ),
//        end = Offset(
//            x = size.width,
//            y = size.height - 1.dp.toPx(),
//        ),
//        strokeWidth = 1.dp.toPx(),
//    )
//}