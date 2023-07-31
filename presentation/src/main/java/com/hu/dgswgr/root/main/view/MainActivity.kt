package com.hu.dgswgr.root.main.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.hu.dgswgr.root.navigation.NavigationGraph
import com.hu.dgswgr.ui.theme.DgswgrTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            DgswgrTheme() {
                Box {
                    NavigationGraph(navController = navController)
                }
                
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
                text = "Hello $name!"
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