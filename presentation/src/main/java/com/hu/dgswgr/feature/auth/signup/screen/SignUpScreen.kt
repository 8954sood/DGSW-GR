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
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hu.dgswgr.feature.auth.signup.mvi.SignUpSideEffect
import com.hu.dgswgr.feature.auth.signup.mvi.SignUpState
import com.hu.dgswgr.feature.auth.signup.vm.SignUpViewModel
import com.hu.dgswgr.root.main.view.MainActivity.Companion.TAG
import com.hu.dgswgr.root.main.vm.MainViewModel
import com.hu.dgswgr.root.navigation.NavGroup
import com.hu.dgswgr.ui.components.appbar.DgswAppBar
import com.hu.dgswgr.ui.components.button.DgswgrDefaultButton
import com.hu.dgswgr.ui.components.loading.LoadInFullScreen
import com.hu.dgswgr.ui.components.textfiled.DgswgrLongTextField
import com.hu.dgswgr.ui.components.textfiled.DgswgrNumberField
import com.hu.dgswgr.ui.theme.Body3
import com.hu.dgswgr.ui.theme.DgswgrTheme
import com.hu.dgswgr.ui.theme.Title1
import com.hu.dgswgr.ui.theme.Title3
import com.hu.dgswgr.utiles.shortToast
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import org.orbitmvi.orbit.compose.collectState

@Composable
fun SignUpScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    val signUpSate = signUpViewModel.collectAsState().value
    val context = LocalContext.current
    val mainState = mainViewModel.collectAsState().value
//    Column() {
//        Text(text = "test2")
//
//
//    }
//    Text(text = "test2")
//    Button(onClick = { navController.popBackStack() }) {
//        Text(text = "돌아가기")
//    }
//    TODO(지금 학번 입략칸이 작아서 사용자 경험에 불편함)
//    LaunchedEffect(Unit) {
//        signUpViewModel.testInputLoading(true)
//        delay(3000)
//        signUpViewModel.testInputLoading(false)
//    }
    LaunchedEffect(Unit) {

        if (mainState.check == true) {
            mainViewModel.inputCheck(false)
        }
    }
    signUpViewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is SignUpSideEffect.SuccessSignUp -> {
                context.shortToast("회원가입에 성공했어요!")
                signUpViewModel.resetPage()
//                navController.popBackStack()
            }
            is SignUpSideEffect.FailSignUp -> {
                context.shortToast(sideEffect.throwable.message ?: "회원가입에 실패했어요.")//context.getString(R.string.desc_join_fail))
            }
            is SignUpSideEffect.SuccessCheck -> {
                context.shortToast("로그인 페이지로 넘어갈게요!")
                signUpViewModel.setPage(10)
            }
            is SignUpSideEffect.FailCheck -> {
                signUpViewModel.setPage(signUpSate.page + 1)
            }
            is SignUpSideEffect.SuccessLogin -> {
                mainViewModel.inputCheck(true)
                navController.navigate(NavGroup.Test.Test1) {
                    popUpTo(NavGroup.Auth.SIGNUP) {
                        inclusive = true
                    }
                }
                context.shortToast("로그인 성공")
            }
            is SignUpSideEffect.FailLogin -> {
                context.shortToast("로그인 실패")
            }
        }
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
        MainScreen1(signUpViewModel, signUpSate)
    }
    AnimatedVisibility(
        visible = visibleCheck(1, signUpSate.page, signUpSate.loading),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
//        PreviewScreen1(signUpViewModel, signUpSate)
        SignUpScreen1(signUpViewModel, signUpSate)
    }
    AnimatedVisibility(
        visible = visibleCheck(2, signUpSate.page, signUpSate.loading),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
//        PreviewScreen1(signUpViewModel, signUpSate)
        SignUpScreen2(signUpViewModel, signUpSate)
    }
    AnimatedVisibility(visible = visibleCheck(10, signUpSate.page, signUpSate.loading),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
//        PreviewScreen1(signUpViewModel, signUpSate)
        LoginScreen1(signUpViewModel, signUpSate)
    }

}

@OptIn(ExperimentalComposeUiApi::class)
//@Preview(showSystemUi = true)

@Composable
private fun MainScreen1(
    signUpViewModel: SignUpViewModel,
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
            text = "로그인 & 회원가입",
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
                        signUpViewModel.inputLoginId(it)
                    }
                )
                Spacer(modifier = Modifier.height(43.dp))
                DgswgrDefaultButton(
                    text = "계속하기",
                    enabled = (loginId.isNotEmpty() && loginId.length > 5),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = btnColor,
                        disabledContainerColor = btnColor
                    ),
                    onClick = { signUpViewModel.checkId(loginId) }
                )
            }
        }
        LaunchedEffect(Unit) {
            firstAnimation += 1
        }

    }
}

@Composable
private fun LoginScreen1(
    signUpViewModel: SignUpViewModel,
    signUpState: SignUpState
) {
    val focus = LocalFocusManager.current
    val password = signUpState.password
    val checked = signUpState.saveLogin
    val btnColor by animateColorAsState(if (password.length >= 8) DgswgrTheme.color.Black else DgswgrTheme.color.Gray, animationSpec = tween(durationMillis = 500, easing = LinearEasing))


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
            text = "로그인",
            onClick = { Log.d("dd ", "") }
        )
        Row(modifier = Modifier
            .padding(16.dp, 0.dp)) {
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
                        signUpViewModel.inputPassword(it)
                    }
                )
                Box {
                    Body3(
                        text = "아직 8자리가 아니에요.",
                        textColor = animateColorAsState(
                            if (password.length >= 8 || password.isEmpty()) DgswgrTheme.color.White else DgswgrTheme.color.Red,
                            animationSpec = tween(durationMillis = 300, easing = LinearEasing)
                        ).value
                    )
                }
                Spacer(modifier = Modifier.height(151.dp))
                Row {
                    Checkbox( // TODO(나중에 디자인 킷 만들어야겠다.. 이거 진짜아니다)
                        modifier = Modifier.size(15.dp),
                        checked = checked,
                        onCheckedChange = {
                            signUpViewModel.inputSavePassword(it)
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Body3(
                        text = "로그인 정보 기억하기",
                        textColor = DgswgrTheme.color.Black80,
                        modifier = Modifier.absoluteOffset(y=(-2).dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                DgswgrDefaultButton(
                    text = "계속하기",
                    enabled = (password.length >= 8),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = btnColor,
//                        disabledContainerColor = btnColor
//                    ),
                    onClick = {
                        signUpViewModel.login(
                            loginId = signUpState.loginId,
                            password = signUpState.password
                        )
                    }//signUpViewModel.setPage(signUpState.page + 1) }
                )
            }
        }
    }
}

@Composable
private fun SignUpScreen1(
    signUpViewModel: SignUpViewModel,
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
                        signUpViewModel.inputPassword(it)
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
                    text = "계속하기",
                    enabled = (password == retryPassword && password.length >= 8),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = btnColor,
                        disabledContainerColor = btnColor
                    ),
                    onClick = { signUpViewModel.setPage(signUpState.page + 1) }
                )
                Spacer(modifier = Modifier.height(7.dp))
                DgswgrDefaultButton(
                    text = "계속하기",
                    onClick = { signUpViewModel.setPage(signUpState.page + 1) }
                )
            }
        }

    }
}

//@Preview(showBackground = true)
@Composable
private fun SignUpScreen2(
    signUpViewModel: SignUpViewModel,
    signUpState: SignUpState
) {
    val focus = LocalFocusManager.current
    val grade = signUpState.grade //by remember { mutableStateOf("") }
    val classNumber = signUpState.classNumber //by remember { mutableStateOf("") }
    val studentNumber = signUpState.studentNumber //by remember { mutableStateOf("") }
    val name = signUpState.name //by remember { mutableStateOf("") }

    val btnColor by animateColorAsState(if (grade.isNotEmpty() && classNumber.isNotEmpty() && studentNumber.isNotEmpty() && name.isNotEmpty()) DgswgrTheme.color.Black else DgswgrTheme.color.Gray, animationSpec = tween(durationMillis = 500, easing = LinearEasing))
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
            onClick = { Log.d("dd ", "") }
        )
        Row(
            modifier = Modifier
                .padding(16.dp, 0.dp)
        ) {
//            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Spacer(modifier = Modifier.height(32.dp))
                Title1(
                    text = "학번 입력"
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    DgswgrNumberField(
                        value = grade,
                        onValueChange = {
                            if (it.isDigitsOnly().not() || it.length > 1) { return@DgswgrNumberField }
                            signUpViewModel.inputGrade(it)
                        }
                    )
                    Title3(
                        text = "학년",
                        textColor = DgswgrTheme.color.Black80,
                        modifier = Modifier.absoluteOffset(y=0.dp)
                    )
                    Spacer(modifier = Modifier.width(11.dp))
                    DgswgrNumberField(
                        value = classNumber,
                        onValueChange = {
                            if (it.isDigitsOnly().not() || it.length > 1) { return@DgswgrNumberField }
                            signUpViewModel.inputClassNumber(it)
                        }
                    )
                    Title3(
                        text = "반",
                        textColor = DgswgrTheme.color.Black80,
                        modifier = Modifier.absoluteOffset(y=0.dp)
                    )
                    Spacer(modifier = Modifier.width(11.dp))
                    DgswgrNumberField(
                        value = studentNumber,
                        maxLength = 2,
                        onValueChange = {
                            if (it.isDigitsOnly().not() || it.length > 2) { return@DgswgrNumberField }
                            signUpViewModel.inputStudentNumber(it)
                        }
                    )
                    Title3(
                        text = "번",
                        textColor = DgswgrTheme.color.Black80,
                        modifier = Modifier.absoluteOffset(y=0.dp)
                    )

                }
                Spacer(modifier = Modifier.height(45.dp))
                Title1(
                    text = "이름 입력"
                )
                Spacer(modifier = Modifier.height(20.dp))
                DgswgrLongTextField(
                    value = name,
                    placeholder = "다시 한번 입력해주세요",
                    onValueChange = {
                        if (it.contains(" ")) { return@DgswgrLongTextField }
                        signUpViewModel.inputName(it)
                    }
                )
                Spacer(modifier = Modifier.height(75.dp))
                DgswgrDefaultButton(
                    text = "회원가입 완료",
                    enabled = (grade.isNotEmpty() && classNumber.isNotEmpty() && studentNumber.isNotEmpty() && name.isNotEmpty()),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = btnColor,
                        disabledContainerColor = btnColor
                    ),
                    onClick = {
                        Log.d(TAG, "SignUpScreen2: $signUpState")
                        signUpViewModel.signUp(
                            loginId = signUpState.loginId,
                            password = signUpState.password,
                            name = signUpState.name,
                            grade = signUpState.grade,
                            classNumber = signUpState.classNumber,
                            studentNumber = signUpState.studentNumber
                        )
                    }
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