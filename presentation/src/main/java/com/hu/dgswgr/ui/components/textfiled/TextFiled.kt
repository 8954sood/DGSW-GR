package com.hu.dgswgr.ui.components.textfiled


import androidx.compose.foundation.background


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hu.dgswgr.root.main.view.MainActivity.Companion.TAG
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
        .height((1).dp),
    type: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit = {}
) {
    var decorationBox = decorationBox
//    val isKeyboardOpen by keyboardAsState() // Keyboard.Opened or Keyboard.Closed
    val focus = LocalFocusManager.current
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    var isFocus by remember { mutableStateOf(false) }
    BasicTextField(
        value = value,
        modifier = Modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocus = it.isFocused
            },
        visualTransformation = if (type == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
        onValueChange = onValueChange,
        textStyle = DgswgrTheme.typography.body1.copy(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = type
        ),
        keyboardActions = KeyboardActions(
            onDone = {
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
//                        if (!isFocus || value.isNotEmpty()) {
                        if (value.isEmpty()) {
                            Body1(
                                text = placeholder,
                                textColor = DgswgrTheme.color.Black20
                            )
                        }
                        innerTextField()
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = if (isFocus || value.isNotEmpty()) decorationBox.background(DgswgrTheme.color.Black) else decorationBox )

            }
        }
    )
}

@Composable
fun DgswgrNumberField(
    value: String = "",
    maxLength: Int = 1,
    textStyle: TextStyle = DgswgrTheme.typography.title3,
    decorationBox: Modifier = Modifier
        .fillMaxWidth()
        .background(DgswgrTheme.color.Black20)
        .height((1).dp),
    type: KeyboardType = KeyboardType.Number,
    onValueChange: (String) -> Unit = {}
) {
    var decorationBox = decorationBox
//    val isKeyboardOpen by keyboardAsState() // Keyboard.Opened or Keyboard.Closed
    val focus = LocalFocusManager.current
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    var isFocus by remember { mutableStateOf(false) }
    BasicTextField(
        value = value,
        modifier = Modifier
            .width((maxLength*12).dp)
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocus = it.isFocused
            },
        onValueChange = onValueChange,
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = type
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focus.clearFocus()
            }
        ),
        decorationBox = { innerTextField ->
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

                Box() {
//                        if (!isFocus || value.isNotEmpty()) {
                    innerTextField()
                }

                Spacer(modifier = Modifier.height(0.dp))
                Box(modifier = if (isFocus || value.isNotEmpty()) decorationBox.background(DgswgrTheme.color.Black) else decorationBox )



            }
        }
    )
}

//@Composable
//fun tes() {
//    TextField(value = , onValueChange = )
//}

//enum class Keyboard {
//    Opened, Closed
//}
//
//@Composable
//fun keyboardAsState(): State<Keyboard> {
//    val keyboardState = remember { mutableStateOf(Keyboard.Closed) }
//    val view = LocalView.current
//    DisposableEffect(view) {
//        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
//            val rect = Rect()
//            view.getWindowVisibleDisplayFrame(rect)
//            val screenHeight = view.rootView.height
//            val keypadHeight = screenHeight - rect.bottom
//            keyboardState.value = if (keypadHeight > screenHeight * 0.15) {
//                Keyboard.Opened
//            } else {
//                Keyboard.Closed
//            }
//        }
//        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)
//
//        onDispose {
//            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
//        }
//    }
//
//    return keyboardState
//}