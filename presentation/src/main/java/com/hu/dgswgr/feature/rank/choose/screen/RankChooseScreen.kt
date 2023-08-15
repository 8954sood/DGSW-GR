package com.hu.dgswgr.feature.rank.choose.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.hu.dgswgr.R
import com.hu.dgswgr.feature.rank.choose.vm.RankChooseViewModel
import com.hu.dgswgr.ui.components.appbar.DgswAppBar
import com.hu.dgswgr.ui.components.loading.LoadInFullScreen
import com.hu.dgswgr.ui.theme.Body0
import com.hu.dgswgr.ui.theme.Body1
import com.hu.dgswgr.ui.theme.Body3
import com.hu.dgswgr.ui.theme.DgswgrTheme
import com.hu.dgswgr.ui.theme.Title2
import com.hu.dgswgr.ui.theme.Title3
import com.hu.dgswgr.utiles.replaceUrl
import com.hu.dgswgr.utiles.toNumber
import org.orbitmvi.orbit.compose.collectAsState
import retrofit2.http.Body


@Composable
fun RankChooseScreen(
    navController: NavController = NavController(LocalContext.current),
    rankChooseViewModel: RankChooseViewModel = hiltViewModel(),
    id: Int = 1,
) {
    val focus = LocalFocusManager.current
    val scrollState = rememberScrollState()
    val rankChooseState = rankChooseViewModel.collectAsState().value

    LaunchedEffect(Unit) {
        rankChooseViewModel.load(id)
    }
    AnimatedVisibility(
        visible = rankChooseState.loading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        LoadInFullScreen()
    }
    AnimatedVisibility(
        visible = rankChooseState.loading.not(),
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
        )
        {
            DgswAppBar(
                text = "유저 정보",
                onClick = { navController.popBackStack() }
            )
            Column(
                modifier = Modifier.verticalScroll(scrollState)
            ) {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = rankChooseState.icon),//painterResource(R.drawable.profile_lol),
                            contentDescription = "show LoL user Profile icon",
                            modifier = Modifier
                                .size(90.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.height(26.dp))
                        Title2(text = rankChooseState.nickname)
                        Spacer(modifier = Modifier.height(5.dp))
                        Body3(text = "Level ${rankChooseState.level}")
                        Spacer(modifier = Modifier.height(26.dp))
                    }

                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        color = DgswgrTheme.color.Gray,
                        thickness = 10.dp
                    )
                    Row(
                        modifier = Modifier
                            .padding(16.dp, 0.dp)
                    ) {
                        Column {
                            Spacer(modifier = Modifier.height(31.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.student),
                                    contentDescription = "show LoL user Profile icon",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                )
                                Spacer(modifier = Modifier.width(19.dp))
                                Body1(
                                    text = "${rankChooseState.grade}${rankChooseState.classId}${rankChooseState.number.toNumber()}\n${rankChooseState.name}",
                                    textColor = DgswgrTheme.color.Black80
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .absoluteOffset(y = (-2).dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.End

                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter(model = rankChooseState.tierIcon.replaceUrl()),//painterResource(R.drawable.lol_diamond),
                                        contentDescription = "show LoL user Profile icon",
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                    )
                                    Spacer(modifier = Modifier.width(18.dp))
                                    Body0(
                                        text = rankChooseState.tierStr,
                                        textColor = DgswgrTheme.color.Black80
                                    )
                                    Spacer(modifier = Modifier.width(11.dp))
                                    Body0(
                                        text = rankChooseState.tierPoint,
                                        textColor = DgswgrTheme.color.Black80
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(37.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Box() {
                                    Row(
                                        verticalAlignment = Alignment.Bottom
                                    ) {
                                        Body0(
                                            text = "K/DA :",
                                            textColor = DgswgrTheme.color.Black80
                                        )
                                        Spacer(modifier = Modifier.width(9.dp))
                                        Body1(
                                            text = "2.31",
                                            textColor = DgswgrTheme.color.Black80
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Body0(
                                            text = rankChooseState.winLose,
                                            textColor = DgswgrTheme.color.DarkGray
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.Bottom,
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        Body0(
                                            text = "승률 :",
                                            textColor = DgswgrTheme.color.Black80
                                        )
                                        Spacer(modifier = Modifier.width(9.dp))
                                        Body1(
                                            text = rankChooseState.winRate,
                                            textColor = DgswgrTheme.color.Black80
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(47.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Title3(
                                    text = "모스트 챔피언"
                                )
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                            Row(modifier = Modifier.height(146.dp)) {
                                Column(
                                    modifier = Modifier
                                        .width(50.dp)
                                        .fillMaxHeight()
                                ) {
                                    Spacer(modifier = Modifier.height(80.dp))
                                    Body0(text = "승률")
                                    Spacer(modifier = Modifier.height(14.dp))
                                    Body0(text = "K/DA")
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Start
                                        ) {
                                            RankMostCard(
                                                icon = rankChooseState.most[0].icon,
                                                winRate = rankChooseState.most[0].winRate,
                                                kda = rankChooseState.most[0].kda
                                            )
                                        }
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            RankMostCard(
                                                icon = rankChooseState.most[1].icon,
                                                winRate = rankChooseState.most[1].winRate,
                                                kda = rankChooseState.most[1].kda
                                            )
                                        }
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.End
                                        ) {
                                            RankMostCard(
                                                icon = rankChooseState.most[2].icon,
                                                winRate = rankChooseState.most[2].winRate,
                                                kda = rankChooseState.most[2].kda
                                            )
                                        }
                                    }
                                }
                            }


                        }
                    }
                }
            }
        }
    }
}


//@Preview(showBackground = true)
@Composable
private fun testPreview(
) {
    Row(modifier = Modifier.height(146.dp)) {
        Column(
            modifier = Modifier
                .width(50.dp)
                .fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Body0(text = "승률")
            Spacer(modifier = Modifier.height(14.dp))
            Body0(text = "K/DA")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    RankMostCard(
                        icon = "",
                        winRate = "54%",
                        kda = "3.14"
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    RankMostCard(
                        icon = "",
                        winRate = "54%",
                        kda = "3.14"
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    RankMostCard(
                        icon = "",
                        winRate = "54%",
                        kda = "3.14"
                    )
                }
            }
        }
    }
}


//@Preview(showBackground = true)
@Composable
private fun RankMostCard(
    icon: String,
    winRate: String,
    kda: String
) {
    Column(
        modifier = Modifier
            .height(146.dp)
            .width(64.dp),
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = icon.replaceUrl()),
            contentDescription = "most champion icon",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Row(modifier = Modifier.padding(13.dp, 0.dp, 0.dp, 0.dp)) {
            Column() {
                Spacer(modifier = Modifier.height(16.dp))
                Body0(text = winRate)
                Spacer(modifier = Modifier.height(14.dp))
                Body0(text = kda)
            }
        }
    }
}

