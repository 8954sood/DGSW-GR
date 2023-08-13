package com.hu.dgswgr.feature.rank.choose.screen

import android.util.Log
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.hu.dgswgr.R
import com.hu.dgswgr.ui.components.appbar.DgswAppBar
import com.hu.dgswgr.ui.theme.Body0
import com.hu.dgswgr.ui.theme.Body1
import com.hu.dgswgr.ui.theme.Body3
import com.hu.dgswgr.ui.theme.DgswgrTheme
import com.hu.dgswgr.ui.theme.Title2
import com.hu.dgswgr.ui.theme.Title3

@Preview(showBackground = true)
@Composable
fun RankChooseScreen(
    navController: NavController = NavController(LocalContext.current),
    id: Int = 1
) {
    val focus = LocalFocusManager.current

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

        Column {
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.profile_lol),
                    contentDescription = "show LoL user Profile icon",
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(26.dp))
                Title2(text = "바비호바")
                Spacer(modifier = Modifier.height(5.dp))
                Body3(text = "Level 156")
                Spacer(modifier = Modifier.height(4.dp))
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
                            text = "1111\n박병준",
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
                                painter = painterResource(R.drawable.lol_diamond),
                                contentDescription = "show LoL user Profile icon",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(18.dp))
                            Body0(
                                text = "Silver 4",
                                textColor = DgswgrTheme.color.Black80
                            )
                            Spacer(modifier = Modifier.width(11.dp))
                            Body0(
                                text = "35LP",
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
                                    text = "51승 51패",
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
                                    text = "51%",
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

                }
            }
        }
    }
}