package com.hu.dgswgr.feature.auth.signup.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hu.dgswgr.feature.auth.signup.vm.SignUpViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SignUpScreen(
    navController: NavController,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    val signUpSate = signUpViewModel.collectAsState().value


    Column() {
        Text(text = "test2")


    }
    Text(text = "test2")
    Button(onClick = { navController.popBackStack() }) {
        Text(text = "돌아가기")
    }


}