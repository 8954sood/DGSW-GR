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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hu.dgswgr.feature.auth.signup.mvi.SignUpState
import com.hu.dgswgr.feature.auth.signup.vm.SignUpViewModel
import com.hu.dgswgr.root.main.view.MainActivity.Companion.TAG
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
//    TODO(State의 숫자에 따라 화면 전환, ㅇㅇ)
//    TODO( visible 조건은 page == 0 && loading.not())
//    TODO( visibleCheck 를 사용해서 체크하자 위에거)
//    TODO(공백 문자는 받지 못하게 검열하자)
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
        visible = visibleCheck(0, signUpSate.page, signUpSate.loading),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        SignUpScreen1(signUpViewModel, signUpSate)
    }
    AnimatedVisibility(
        visible = visibleCheck(1, signUpSate.page, signUpSate.loading),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
//        PreviewScreen1(signUpViewModel, signUpSate)
        SignUpScreen2(signUpViewModel, signUpSate)
    }

}

@OptIn(ExperimentalComposeUiApi::class)
//@Preview(showSystemUi = true)
@Composable
private fun SignUpScreen1(
    singUpViewModel: SignUpViewModel,
    signUpState: SignUpState
) {
    val focus = LocalFocusManager.current
    val loginId = signUpState.loginId
    var firstAnimation by remember { mutableStateOf(0) }
    val titleColor by animateColorAsState(if (firstAnimation > 0) DgswgrTheme.color.Black else DgswgrTheme.color.White, animationSpec = tween(durationMillis = 700, easing = LinearEasing), finishedListener = { firstAnimation += 1 })
    val titleColor2 by animateColorAsState(if (firstAnimation > 1) DgswgrTheme.color.Black else DgswgrTheme.color.White, animationSpec = tween(durationMillis = 700, easing = LinearEasing))
    val btnColor by animateColorAsState(if (loginId.isNotEmpty() && loginId.length > 5) DgswgrTheme.color.Black else DgswgrTheme.color.Gray, animationSpec = tween(durationMillis = 500, easing = LinearEasing))
    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
//        .verticalScroll(scrollState)
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
                    onClick = { singUpViewModel.setPage(signUpState.page + 1) }
                )
            }
        }
        LaunchedEffect(Unit) {
            firstAnimation += 1
        }

    }
}

@Composable
private fun SignUpScreen2(
    singUpViewModel: SignUpViewModel,
    signUpState: SignUpState
) {
    val focus = LocalFocusManager.current
    val password = signUpState.password
    var retryPassword by remember { mutableStateOf("") }
    val btnColor by animateColorAsState(if (password == retryPassword && password.length >= 8) DgswgrTheme.color.Black else DgswgrTheme.color.Gray, animationSpec = tween(durationMillis = 500, easing = LinearEasing))


    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
//        .verticalScroll(scrollState)
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
                    text = "비밀번호 입력"
                )
                Spacer(modifier = Modifier.height(20.dp))
                DgswgrLongTextField(
                    value = password,
                    placeholder = "비밀번호를 입력해주세요.",
                    decorationBox = Modifier
                        .fillMaxWidth()
                        .background(DgswgrTheme.color.Black40)
                        .height((0.5).dp),
                    type = KeyboardType.Password,
                    onValueChange = {
                        singUpViewModel.inputPassword(it)
                    }
                )
                Box {// TODO(나중에 아마 에러가 여러개일거라서 만듬.)
                    Body3(
                        text = "아직 8자리가 아니에요.",
                        textColor = animateColorAsState(if (password.length >= 8 || password.isEmpty()) DgswgrTheme.color.White else DgswgrTheme.color.Red, animationSpec = tween(durationMillis = 300, easing = LinearEasing)).value
//                        textColor = if (password.length >= 8 || password.isEmpty()) DgswgrTheme.color.White else DgswgrTheme.color.Red
                    )
                }
                Spacer(modifier = Modifier.height(28.dp))
                Title1(
                    text = "비밀번호 확인"
                )
                Spacer(modifier = Modifier.height(20.dp))
                DgswgrLongTextField(
                    value = retryPassword,
                    placeholder = "다시 한번 입력해주세요.",
                    decorationBox = Modifier
                        .fillMaxWidth()
                        .background(DgswgrTheme.color.Black40)
                        .height((0.5).dp),
                    type = KeyboardType.Password,
                    onValueChange = {
                        retryPassword = it
                    }
                )
                Body3(
                    text = "비밀번호가 일치하지 않아요.",
                    textColor = animateColorAsState(if (password == retryPassword || retryPassword.isEmpty()) DgswgrTheme.color.White else DgswgrTheme.color.Red, animationSpec = tween(durationMillis = 500, easing = LinearEasing)).value
                ) //327 370 26
                Spacer(modifier = Modifier.height(26.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Body3(
                        text = "다음이 마지막 단계에요!",
                        textColor = DgswgrTheme.color.Black40
                    )
                }
                Spacer(modifier = Modifier.height(7.dp))
                DgswgrDefaultButton(
                    text = "게속하기",
                    enabled = (password == retryPassword && password.length >= 8),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = btnColor,
                        disabledContainerColor = btnColor
                    ),
                    onClick = { Log.d(TAG, "SignUpScreen2: $signUpState") }
                )
            }
        }

    }
}


private fun visibleCheck(page: Int, nowPage: Int, loading: Boolean): Boolean {
    return (page == nowPage && loading.not())
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