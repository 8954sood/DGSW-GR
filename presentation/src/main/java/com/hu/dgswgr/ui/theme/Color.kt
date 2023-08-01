package com.hu.dgswgr.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

object DgswgrColor {
    val White = Color(0xFFFFFFFF)

    val Purple80 = Color(0xFFD0BCFF)
    val PurpleGrey80 = Color(0xFFCCC2DC)
    val Pink80 = Color(0xFFEFB8C8)

    val Purple40 = Color(0xFF6650a4)
    val PurpleGrey40 = Color(0xFF625b71)
    val Pink40 = Color(0xFF7D5260)

    val Black = Color(0xFF000000)
    val Black80 = Color(0xCC000000)
    val Black60 = Color(0x99000000)
    val Black40 = Color(0x66000000)
    val Black20 = Color(0x33000000)
}
internal val LocalColor = staticCompositionLocalOf { DgswgrColor }