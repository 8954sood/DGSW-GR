package com.hu.dgswgr.ui.components.textfiled

import android.database.Cursor
import android.graphics.Color
import android.graphics.Rect
import android.util.Log
import android.view.ViewTreeObserver
import androidx.compose.foundation.background


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hu.dgswgr.ui.theme.Body1
import com.hu.dgswgr.ui.theme.DgswgrTheme






@Preview(showSystemUi = true)
@Composable
fun DgswgrLongTextField(
    value: String = "",
    placeholder: String = "아이디를 입력하세요.",
    decorationBox: Modifier = Modifier
        .fillMaxWidth()
        .background(DgswgrTheme.color.Black40)
        .height((0.5).dp),
    type: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit = {}
) {
    var decorationBox = decorationBox
    var isFocused by remember { mutableStateOf(false) }
    val isKeyboardOpen by keyboardAsState() // Keyboard.Opened or Keyboard.Closed
    val focus = LocalFocusManager.current
    BasicTextField(
        value = value,
        modifier = Modifier
            .onFocusChanged {

//            focus.clearFocus()
            Log.d("LOG", "DgswgrLongTextField: onFoucsChanged Called")
       },
        onValueChange = onValueChange,
        textStyle = DgswgrTheme.typography.body1.copy(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
//        cursorBrush = Brush.verticalGradient(
//            0.90f to Color.Black
//        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = type
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                // Keyboard 숨기기
                focus.clearFocus()
            }
        ),
        decorationBox = { innerTextField ->
            Column {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                )
                {
                    Box() {
                        if (value.isEmpty() && isKeyboardOpen == Keyboard.Closed) {
//                            Text(
//                                text = placeholder,
//                                style = DgswgrTheme.typography.body1,
//                                color = DgswgrTheme.color.Black20
//                            )
                            Body1(
                                text = placeholder,
                                textColor = DgswgrTheme.color.Black20
                            )
                        }
                        innerTextField()
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = if (isKeyboardOpen == Keyboard.Opened || value.isNotEmpty()) decorationBox.background(DgswgrTheme.color.Black80) else decorationBox )
                    //.padding(0.dp, 0.dp, 16.dp, 0.dp)

            }
        }
    )
}

//@Composable
//fun tes() {
//    TextField(value = , onValueChange = )
//}

enum class Keyboard {
    Opened, Closed
}

@Composable
fun keyboardAsState(): State<Keyboard> {
    val keyboardState = remember { mutableStateOf(Keyboard.Closed) }
    val view = LocalView.current
    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardState.value = if (keypadHeight > screenHeight * 0.15) {
                Keyboard.Opened
            } else {
                Keyboard.Closed
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }

    return keyboardState
}