package com.hu.dgswgr.ui.components.line

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hu.dgswgr.ui.theme.DgswgrTheme

@Preview(showBackground = true)
@Composable
fun dgswgrLine(
    color: Color = DgswgrTheme.color.Black20,
    height: Dp = (0.5).dp
) {
    Box(modifier = Modifier.fillMaxWidth().background(color).height(height))
}