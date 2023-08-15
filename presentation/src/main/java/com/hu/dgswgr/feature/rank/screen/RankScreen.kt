package com.hu.dgswgr.feature.rank.screen

import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.hu.dgswgr.feature.home.screen.HomeDialog
import com.hu.dgswgr.feature.rank.vm.RankViewModel
import com.hu.dgswgr.root.navigation.NavGroup
import com.hu.dgswgr.ui.components.appbar.DgswAppBar
import com.hu.dgswgr.ui.components.loading.LoadInFullScreen
import com.hu.dgswgr.ui.items.DgswgrRankItemsLol
import com.hu.dgswgr.ui.theme.Body1
import com.hu.dgswgr.ui.theme.Body3
import com.hu.dgswgr.ui.theme.DgswgrTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun RankScreen(
    navController: NavController,
    rankViewModel: RankViewModel = hiltViewModel()
) {

    val rankState = rankViewModel.collectAsState().value

    val focus = LocalFocusManager.current

    LaunchedEffect(Unit) {
        if (rankState.firstTime) {
            rankViewModel.load("tier")
        }
    }

    AnimatedVisibility(
        visible = rankState.loading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        LoadInFullScreen()
    }
    AnimatedVisibility(
        visible = rankState.loading.not(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            //        .verticalScroll(scrollState)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focus.clearFocus()

                })
            }
        ) {
            DgswAppBar(
                text = "랭킹",
                buttonVisible = false
            )
            Row(
                modifier = Modifier
                    .padding(0.dp, 13.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                RankButton(
                    text = "티어",
                    onClick = {
                        rankViewModel.load("tier")
                    }
                )
                Spacer(modifier = Modifier.width(13.dp))
                RankButton(
                    text = "레벨",
                    onClick = {
                        rankViewModel.load("level")
                    }
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            Divider(
                color = DgswgrTheme.color.Black20,
                modifier = Modifier
                    .fillMaxWidth()
            )
            LazyColumn() {
                itemsIndexed(
                    rankState.list
                ) { index, item ->
                    DgswgrRankItemsLol(
                        name = item.name,
                        profile_icon = item.icon,
                        tier_icon = item.tierIcon,
                        tier_str = item.tierStr,
                        level = item.level,
                        studentInfo = item.student,
                        onClick = {
                            navController.navigate(
                                NavGroup.Rank.CHOOSE.replace(
                                    oldValue = "{id}",
                                    newValue = item.id.toString(),
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun RankButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .border(
                width = (0.5).dp,
                color = DgswgrTheme.color.Black60,
                shape = RoundedCornerShape(20.dp)
            )
    ) {

        Body3(
            text = text,
            textColor =DgswgrTheme.color.Black60,
            modifier =Modifier
                .padding(12.dp, 2.dp),
        )
    }
}