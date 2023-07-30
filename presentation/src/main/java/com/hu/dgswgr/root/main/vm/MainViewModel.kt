package com.hu.dgswgr.root.main.vm

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
class MainViewModel: ViewModel() {
    val text = mutableStateOf("이")
}