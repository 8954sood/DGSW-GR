package com.hu.dgswgr.feature.auth.signup.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material.ButtonColors
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.hu.dgswgr.feature.auth.signup.mvi.SignUpState
import com.hu.dgswgr.feature.auth.signup.vm.SignUpViewModel
import com.hu.dgswgr.ui.components.appbar.DgswAppBar
import com.hu.dgswgr.ui.components.button.DgswgrDefaultButton
import com.hu.dgswgr.ui.components.loading.LoadInFullScreen
import com.hu.dgswgr.ui.components.textfiled.DgswgrLongTextField
import com.hu.dgswgr.ui.theme.Body3
import com.hu.dgswgr.ui.theme.DgswgrTheme
import com.hu.dgswgr.ui.theme.Title1
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SignUpScreen(
    navController: NavController,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    val signUpSate = signUpViewModel.collectAsState().value

//    Column() {
//        Text(text = "test2")
//
//
//    }
//    Text(text = "test2")
//    Button(onClick = { navController.popBackStack() }) {
//        Text(text = "돌아가기")
//    }
    LaunchedEffect(Unit) {
        signUpViewModel.testInputLoading(true)
        delay(3000)
        signUpViewModel.testInputLoading(false)
    }
    AnimatedVisibility(
        visible  = signUpSate.loading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        LoadInFullScreen()
    }
    AnimatedVisibility(
        visible = signUpSate.loading.not(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        PreviewScreen1(signUpViewModel, signUpSate)
    }

}

@OptIn(ExperimentalComposeUiApi::class)
//@Preview(showSystemUi = true)
@Composable
fun PreviewScreen1(
    singUpViewModel: SignUpViewModel,
    signUpState: SignUpState
) {
    val focus = LocalFocusManager.current
    val loginId = signUpState.loginId
    val test = remember { mutableStateOf(0) }
    val titleColor by animateColorAsState(if (test.value > 0) DgswgrTheme.color.Black else DgswgrTheme.color.White, animationSpec = tween(durationMillis = 700, easing = LinearEasing), finishedListener = { test.value += 1 })
    val titleColor2 by animateColorAsState(if (test.value > 1) DgswgrTheme.color.Black else DgswgrTheme.color.White, animationSpec = tween(durationMillis = 700, easing = LinearEasing))
    val btnColor by animateColorAsState(if (loginId.isNotEmpty() && loginId.length > 5) DgswgrTheme.color.Black else DgswgrTheme.color.Gray, animationSpec = tween(durationMillis = 500, easing = LinearEasing))

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
        DgswAppBar(
            text = "회원가입",
            onClick = { Log.d("dd ", "")}
        )
        Row(modifier = Modifier
            .padding(16.dp, 0.dp)) {
//            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Spacer(modifier = Modifier.height(32.dp))
                Title1(
                    text = "여러분의 게임랭크를",
                    textColor = titleColor
                )
                Spacer(modifier = Modifier.height(2.dp))
                Title1(
                    text = "자랑해볼까요?",
                    textColor = titleColor2
                )
                Spacer(modifier = Modifier.height(106.dp))
                Body3(
                    text = "먼저 로그인이 필요해요 :)",
                    textColor = DgswgrTheme.color.Black40
                )
                Spacer(modifier = Modifier.height(16.dp))
                DgswgrLongTextField(
                    value = loginId,
                    decorationBox = Modifier
                        .fillMaxWidth()
                        .background(DgswgrTheme.color.Black40)
                        .height((0.5).dp),
                    onValueChange = {
                        singUpViewModel.inputLoginId(it)
                    }
                )
                Spacer(modifier = Modifier.height(43.dp))
                DgswgrDefaultButton(
                    text = "게속하기",
                    enabled = (loginId.isNotEmpty() && loginId.length > 5),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = btnColor,
                        disabledContainerColor = btnColor
                    ),
                    onClick = {  }
                )
            }
        }
        LaunchedEffect(Unit) {
            test.value += 1
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