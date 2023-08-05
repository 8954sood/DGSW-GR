package com.hu.dgswgr.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.hu.dgswgr.ui.theme.DgswgrColor.Pink40
import com.hu.dgswgr.ui.theme.DgswgrColor.Pink80
import com.hu.dgswgr.ui.theme.DgswgrColor.Purple40
import com.hu.dgswgr.ui.theme.DgswgrColor.Purple80
import com.hu.dgswgr.ui.theme.DgswgrColor.PurpleGrey40
import com.hu.dgswgr.ui.theme.DgswgrColor.PurpleGrey80

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)
@Composable
fun DgswgrTheme(
    color: DgswgrColor = DgswgrTheme.color,
    typography: DgswgrTypography = DgswgrTheme.typography,
    shape: DgswgrShape = DgswgrTheme.shape,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColor provides color,
        LocalTypography provides typography,
        LocalShape provides shape
    ) {
        content()
    }
}
object DgswgrTheme {
    val color: DgswgrColor
        @Composable
        @ReadOnlyComposable
        get() = LocalColor.current

    val typography: DgswgrTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shape: DgswgrShape
        @Composable
        @ReadOnlyComposable
        get() = LocalShape.current
}

//
//@Composable
//fun DgswgrTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
//    content: @Composable () -> Unit
//) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//
//    )
//}