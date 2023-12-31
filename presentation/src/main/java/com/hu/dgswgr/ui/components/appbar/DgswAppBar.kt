package com.hu.dgswgr.ui.components.appbar

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hu.dgswgr.R
import com.hu.dgswgr.ui.components.line.dgswgrLine
import com.hu.dgswgr.ui.theme.DgswgrTheme
import com.hu.dgswgr.ui.theme.Title3

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DgswAppBar(
    text: String = "테스트",
    buttonVisible: Boolean = true,
    onClick: () -> Unit = { Log.d("앱바 클릭", "테스트") }
) {
    Column(modifier = Modifier
        .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.height(60.dp)
        ) {
            if (buttonVisible) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onClick,
//                        modifier = Modifier.offset(y = (-8).dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_left_24),
                            contentDescription = ""
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Title3(text = text)
            }
        }
//        Spacer(modifier = Modifier.height((18.5).dp))
        dgswgrLine(
            color = DgswgrTheme.color.Black60
        )
    }
}