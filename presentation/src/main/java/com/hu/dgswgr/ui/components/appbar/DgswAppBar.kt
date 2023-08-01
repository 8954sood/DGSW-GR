package com.hu.dgswgr.ui.components.appbar

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hu.dgswgr.R
import com.hu.dgswgr.ui.components.line.dgswgrLine
import com.hu.dgswgr.ui.theme.DgswgrTheme
import com.hu.dgswgr.ui.theme.Title3
import com.hu.dgswgr.ui.theme.noToSansKR

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DgswAppBar(
    text: String = "테스트",
    onClick: () -> Unit = { Log.d("앱바 클릭", "테스트") }
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(33.dp))
        Box {
            Row {
                IconButton(
                    onClick = onClick,
                    modifier = Modifier.offset(y=(-7).dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_left_24),
                        contentDescription = ""
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Title3(text = text)
            }
        }
        Spacer(modifier = Modifier.height((18.5).dp))
        dgswgrLine(
            color = DgswgrTheme.color.Black60
        )
    }
}