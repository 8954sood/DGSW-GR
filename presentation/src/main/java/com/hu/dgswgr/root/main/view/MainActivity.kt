package com.hu.dgswgr.root.main.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.hu.dgswgr.root.navigation.BottomNavigation
import com.hu.dgswgr.root.main.mvi.MainSideEffect
import com.hu.dgswgr.root.main.mvi.MainState
import com.hu.dgswgr.root.main.vm.MainViewModel
import com.hu.dgswgr.root.navigation.NavigationGraph
import com.hu.dgswgr.ui.theme.DgswgrTheme
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.observe(this, state = ::render, sideEffect = ::handleSideEffect)
        mainViewModel.checkToken()
    }

    private fun render(state: MainState) {
        state.check?.let { check ->
            setContent {
                val navController = rememberNavController()
                DgswgrTheme()
                {
                    Scaffold(
                        bottomBar = { if (check) BottomNavigation(navController = navController) }
                    ) {
                        Box(Modifier.padding(it)) {
                            NavigationGraph(navController = navController, tokenCheck = check, viewModel = mainViewModel)
                        }
//                    Box {
//                        NavigationGraph(navController = navController, tokenCheck = it)
//                    }
                    }
                }

            }
        }

    }

    private fun handleSideEffect(sideEffect: MainSideEffect) {
        when (sideEffect) {
            is MainSideEffect.ToastCheckTokenErrorMessage -> {
                Log.e("MainActivity", sideEffect.throwable.stackTraceToString())
                Toast.makeText(this, sideEffect.throwable.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val TAG = "LOG"
    }
}


@Composable
fun testCode() {
    DgswgrTheme {
        var buttonClickCnt by remember {
            mutableStateOf(10)
        }
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background//MaterialTheme.colorScheme.background
        ) {
            Greeting("Android", buttonClickCnt, onClicked = {
                Log.d(MainActivity.TAG, "onCreate: dd")
                buttonClickCnt += 1
            })
        }
    }
}
@Composable
fun Greeting(name: String, clickCnt: Int, onClicked: () -> Unit) {

    Surface(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        border = BorderStroke(1.dp, Color.LightGray),
        shadowElevation = 10.dp
    ) {
        Column() {
            Text(
                text = "Hello $name!",
//                color = textColor
            )
            Text(
                text = "$clickCnt"
            )
            Button(onClicked) {
                Text(text = "클릭")
            }


        }
    }


}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    DgswgrTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            Greeting("Android")
//        }
//    }
//}
//@Preview(showBackground = true)
//@Composable
//fun previewUI() {
//
//}