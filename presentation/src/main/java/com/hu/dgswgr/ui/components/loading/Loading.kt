package com.hu.dgswgr.ui.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hu.dgswgr.R
import com.hu.dgswgr.ui.theme.DgswgrTheme

@Composable
fun LoadInFullScreen(
    modifier: Modifier = Modifier,
    background: Color = DgswgrTheme.color.White,
) {

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.anim_loading)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Box(
        modifier = modifier
            .background(color = background)
            .fillMaxSize()
    ) {
        LottieAnimation(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.Center),
            composition = composition,
            progress = { progress },

            )
    }
}
