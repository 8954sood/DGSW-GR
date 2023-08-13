package com.hu.dgswgr.feature.home.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.hu.dgswgr.R
import com.hu.dgswgr.feature.auth.signup.vm.SignUpViewModel
import com.hu.dgswgr.feature.home.mvi.HomeSideEffect
import com.hu.dgswgr.feature.home.mvi.HomeState
import com.hu.dgswgr.feature.home.vm.HomeViewModel
import com.hu.dgswgr.root.main.view.MainActivity.Companion.TAG
import com.hu.dgswgr.root.navigation.NavGroup
import com.hu.dgswgr.ui.components.appbar.DgswAppBar
import com.hu.dgswgr.ui.components.button.DgswgrDefaultButton
import com.hu.dgswgr.ui.components.loading.LoadInFullScreen
import com.hu.dgswgr.ui.components.textfiled.DgswgrLongTextField
import com.hu.dgswgr.ui.theme.Body1
import com.hu.dgswgr.ui.theme.DgswgrTheme
import com.hu.dgswgr.ui.theme.Title2
import com.hu.dgswgr.utiles.DgswgrString
import com.hu.dgswgr.utiles.shortToast
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


//@Preview(showBackground = true)
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val homeState = homeViewModel.collectAsState().value

    val focus = LocalFocusManager.current
    val context = LocalContext.current

    homeViewModel.collectSideEffect {
        when (it) {
            is HomeSideEffect.ToastError -> {
                if (it.exception.message == context.getString(R.string.text_session)) {
                    navController.navigate(NavGroup.Auth.SIGNUP) {
                        popUpTo(NavGroup.Home.HOME) {
                            inclusive = true
                        }
                    }
                }
                context.shortToast(it.exception.message ?: context.getString(R.string.content_unknown_exception))
                Log.e("HomeScreenError", it.exception.stackTraceToString())
            }
            is HomeSideEffect.FailCreateUser -> {
                context.shortToast(it.exception.message ?: "정보 등록에 실패하였습니다.")
            }
        }
    }

    var showDialog by remember { mutableStateOf(false) }
    AnimatedVisibility(
        visible  = homeState.loading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        LoadInFullScreen()
    }
    AnimatedVisibility(
        visible = homeState.loading.not(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            //        .verticalScroll(scrollState)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focus.clearFocus()

                })
            }
        ) {
            if (showDialog) {
                HomeDialog(
                    title = "롤 정보 입력",
                    homeViewModel = homeViewModel,
                    homeState = homeState,
                    onDismissRequest = { showDialog = false }
                )
            }
            DgswAppBar(
                text = "내정보",
                buttonVisible = false
            )
            Row(
                modifier = Modifier
                    .padding(16.dp, 0.dp)
            ) {
                Column {
                    Spacer(modifier = Modifier.height(38.dp))
                    Row(
                        modifier = Modifier
                            .height(54.dp)
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier.fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.student),
                                contentDescription = "Contact profile picture",
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .size(50.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(15.dp))
                        Column {
                            Title2(text = homeState.name)
                            Body1(text = "${homeState.grade}학년 ${homeState.classNumber}반 ${homeState.studentNumber}번")
                        }
                    }
                    Spacer(modifier = Modifier.height(38.dp))
                    HomeCard(
                        text = "롤 정보 등록하기",
                        painter = painterResource(id = R.drawable.lol),
                        onClick = {
                            showDialog = true

                        }
                    )
                }

            }
        LaunchedEffect(Unit) {
            homeViewModel.load()
        }

        }

    }


}


@Composable
private fun HomeCard(
    text: String,
    painter: Painter,
    onClick: () -> Unit = { Log.d(TAG, "homeCard: test") },
) {
    Box(modifier = Modifier.clickable(onClick=onClick)) {
        Row(modifier = Modifier
            .border(
                width = (0.5).dp,
                color = DgswgrTheme.color.Black,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp, 14.dp)
            .height(80.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painter,
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Body1(text = text)
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeDialog(
    title: String,
    homeViewModel: HomeViewModel,
    homeState: HomeState,
    onDismissRequest: () -> Unit = {}
) {

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier
                .width(300.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(10.dp),
            color = Color.White
        ) {
            Column {
                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                        .fillMaxWidth()
                )
                Row(modifier = Modifier.padding(16.dp, 0.dp)) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Title2(
                            text = title,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(31.dp))
                        DgswgrLongTextField(
                            value=homeState.nickname,
                            placeholder = "닉네임을 입력하세요.",
                            onDone = {
                                homeViewModel.search(name = homeState.nickname)
                                Log.d(TAG, "HomeDialog: onDone")
                            },
                            onValueChange = {
                                homeViewModel.inputNickname(it)
                            }
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        Row(
                            modifier = Modifier
                                .height(60.dp)
                                .fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier.fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(model = homeState.imageUrl),//painterResource(id = R.drawable.profile_lol),
                                    contentDescription = "load image from web",
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .size(60.dp)
                                        .clip(CircleShape)
                                )
                            }
                            Spacer(modifier = Modifier.width(15.dp))
                            Column {
                                Title2(
                                    text = homeState.lolName,
                                    modifier = Modifier
                                        .absoluteOffset(y=3.dp)
                                )
                                Body1(
                                    text = "Level ${homeState.level}"
                                )
                            }

                        }
                        Spacer(modifier = Modifier.height(50.dp))
                        DgswgrDefaultButton(
                            text = "등록하기",
                            enabled = homeState.lolName.isNotEmpty(),
                            onClick = {
                                onDismissRequest()
                                homeViewModel.create(homeState.lolName)
                            }
                        )
                    }



                }
                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}