package com.hu.dgswgr.root.main.vm

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.hu.dgswgr.domain.usecase.token.CheckTokenUseCase
import com.hu.dgswgr.root.main.mvi.MainSideEffect
import com.hu.dgswgr.root.main.mvi.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkTokenUseCase: CheckTokenUseCase,
): ContainerHost<MainState, MainSideEffect>, ViewModel() {
    override val container: Container<MainState, MainSideEffect> = container(MainState())

    fun checkToken() = intent {
        viewModelScope.launch {
            checkTokenUseCase().onSuccess {
                reduce {
                    state.copy(
                        check = it
                    )
                }
            }.onFailure {
                postSideEffect(MainSideEffect.ToastCheckTokenErrorMessage(it))
            }
        }
    }

    fun inputCheck(boolean: Boolean) = intent {
        reduce {
            state.copy(
                check = boolean
            )
        }
    }



}