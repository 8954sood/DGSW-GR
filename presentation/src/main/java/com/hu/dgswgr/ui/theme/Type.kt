package com.hu.dgswgr.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hu.dgswgr.R

val noToSansKR = FontFamily(
    Font(R.font.notosanskr_black, FontWeight.Black, FontStyle.Normal),
    Font(R.font.notosanskr_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.notosanskr_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.notosanskr_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.notosanskr_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.notosanskr_thin, FontWeight.ExtraLight, FontStyle.Normal)
)
// Set of Material typography styles to start with
object DgswgrTypography{
    @Stable
    val body0 = TextStyle(
        fontFamily = noToSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 20.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

    @Stable
    val body1 = TextStyle(
        fontFamily = noToSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

    @Stable
    val body3 = TextStyle(
        fontFamily = noToSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )

    )

    @Stable
    val title1 = TextStyle(
        fontFamily = noToSansKR,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 26.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

    @Stable
    val title2 = TextStyle(
        fontFamily = noToSansKR,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

    @Stable
    val title3 = TextStyle(
        fontFamily = noToSansKR,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 22.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

//    @Stable

    @Stable
    val label1 = TextStyle(
        fontFamily = noToSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

}

@Composable
fun Body0(
    modifier: Modifier = Modifier.padding(vertical = 0.dp),
    text: String,
    textColor: Color = DgswgrTheme.color.Black60
) {
    Text(
        modifier = modifier,
        text = text,
        style = DgswgrTypography.body0,
        color = textColor,
    )
}

@Composable
fun Body1(
    modifier: Modifier = Modifier.padding(vertical = 0.dp),
    text: String,
    textColor: Color = DgswgrTheme.color.Black60
) {
    Text(
        modifier = modifier,
        text = text,
        style = DgswgrTypography.body1,
        color = textColor,
    )
}

@Composable
fun Body3(
    modifier: Modifier = Modifier.padding(vertical = 0.dp),
    text: String,
    textColor: Color = DgswgrTheme.color.Black60
) {
    Text(
        modifier = modifier,
        text = text,
        style = DgswgrTypography.body3,
        color = textColor,
    )
}

@Composable
fun Title1(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = DgswgrTheme.color.Black
) {
    Text(
        modifier = modifier,
        text = text,
        style = DgswgrTypography.title1,
        color = textColor,
    )
}

@Composable
fun Title2(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = DgswgrTheme.color.Black
) {
    Text(
        modifier = modifier,
        text = text,
        style = DgswgrTypography.title2,
        color = textColor,
    )
}

@Composable
fun Title3(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = DgswgrTheme.color.Black
) {
    Text(
        modifier = modifier,
        text = text,
        style = DgswgrTypography.title3,
        color = textColor,
    )
}


@Composable
fun Label1(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = LocalContentColor.current,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    textOverflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Text(
        modifier = modifier,
        text = text,
        style = DgswgrTypography.label1,
        color = textColor,
//        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
    )
}

@Preview
@Composable
fun test() {
    Body3(text = "엄")
}
@Preview
@Composable
fun tes() {
    Label1(text = "암")
}


internal val LocalTypography = staticCompositionLocalOf { DgswgrTypography }