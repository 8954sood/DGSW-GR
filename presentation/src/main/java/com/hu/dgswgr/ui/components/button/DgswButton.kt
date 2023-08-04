package com.hu.dgswgr.ui.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hu.dgswgr.R
import com.hu.dgswgr.ui.theme.Body1
import com.hu.dgswgr.ui.theme.DgswgrTheme


@Composable
private fun DgswgrButton(
    text: String,
    textColor: Color,
    modifier: Modifier,
    buttonColors: ButtonColors,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = buttonColors,
        shape = RoundedCornerShape(5.dp),
        onClick = onClick,
        enabled = enabled
    ) {
        Body1(
            text = text,
            textColor = textColor
        )
    }
}

@Composable
fun DgswgrDefaultButton(
    text: String,
    textColor: Color = DgswgrTheme.color.White,
    modifier: Modifier = Modifier.fillMaxWidth().height(50.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = DgswgrTheme.color.Black, disabledContainerColor = DgswgrTheme.color.Gray),
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    DgswgrButton(
        text = text,
        textColor = textColor,
        modifier = modifier,
        buttonColors = colors,
        enabled = enabled,
        onClick = onClick
    )
}