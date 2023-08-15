package com.hu.dgswgr.ui.items

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.hu.dgswgr.R
import com.hu.dgswgr.root.main.view.MainActivity.Companion.TAG
import com.hu.dgswgr.ui.theme.Body3
import com.hu.dgswgr.ui.theme.DgswgrTheme
import com.hu.dgswgr.ui.theme.Title2
import com.hu.dgswgr.utiles.replaceUrl


@Composable
fun DgswgrRankItemsLol(
    name: String = "바비호바",
    profile_icon: String? = null,
    tier_icon: String? = null,
    tier_str: String = "Silver 4",
    level: Int = 138,
    studentInfo: String = "1113박박박",
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
    ) {
        Column {
            Row(modifier = Modifier.padding(16.dp, 17.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(model = profile_icon),//painterResource(id = profile_icon),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        .size(75.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(24.dp))
                Column {
                    Spacer(modifier = Modifier.height(15.dp))
    //                Text(text = name, fontWeight = FontWeight.Medium, fontSize = 20.sp)
                    Title2(text = name)
                    Spacer(modifier = Modifier.height(12.dp))
    //                Text(text = "ㅇ이")ㅌ/
                    Row {
                        Box {
                            Image(
                                painter = rememberAsyncImagePainter(model = tier_icon?.replaceUrl()),//painterResource(id = tier_icon),
                                contentDescription = "LOL Rank Tier Icon",
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .absoluteOffset(y = (0).dp) //-3
                            )
                            //                    Spacer(modifier = Modifier.width(8.dp))
                            Body3(
                                text = tier_str,
                                modifier = Modifier.offset(x= 28.dp)
                            )
                            Body3(
                                text = "Level $level",
                                modifier = Modifier.offset(x= 93.dp),
                            )
                            Body3(
                                text = studentInfo,
                                modifier = Modifier
                                    .offset(x = 164.dp)
                                    .absoluteOffset(y = (-1).dp)
                            )
                        }

                    }
                }

            }
    //        Box(modifier = Modifier
    //            .fillMaxWidth()
    //            .background(DgswgrTheme.color.Black20)
    //            .height((0.5).dp))
            Divider(
                color = DgswgrTheme.color.Black20,
                modifier = Modifier
                    .fillMaxWidth()
                )
        }
    }
}

//@Composable
//fun profileDescription(
//    text: String,
//    fontSize: TextUnit = 12.sp,
//    textColor: Color = DgswgrTheme.color.Black60,
//    modifier: Modifier = Modifier
//) {
//    Text(
//        text = text,
//        color = textColor,
//        fontSize = fontSize,
//        modifier = modifier
//    )
//}