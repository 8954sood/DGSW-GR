package com.hu.dgswgr.feature.home.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hu.dgswgr.domain.usecase.home.HomeInfoUseCase
import com.hu.dgswgr.domain.usecase.lol.LolCreateUseCase
import com.hu.dgswgr.domain.usecase.lol.LolSearchUseCase
import com.hu.dgswgr.feature.auth.signup.mvi.SignUpSideEffect
import com.hu.dgswgr.feature.auth.signup.mvi.SignUpState
import com.hu.dgswgr.feature.home.mvi.HomeSideEffect
import com.hu.dgswgr.feature.home.mvi.HomeState
import com.hu.dgswgr.root.main.view.MainActivity.Companion.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeInfoUseCase: HomeInfoUseCase,
    private val lolSearchUseCase: LolSearchUseCase,
    private val lolCreateUseCase: LolCreateUseCase
): ContainerHost<HomeState, HomeSideEffect>, ViewModel() {
    override val container = container<HomeState, HomeSideEffect>(HomeState())

    fun load() = intent {
        reduce {
            state.copy(
                loading = true
            )
        }
        homeInfoUseCase().onSuccess { userInfo ->
            reduce {
                state.copy(
                    name = userInfo.name,
                    grade = userInfo.grade,
                    classNumber = userInfo.classNumber,
                    studentNumber = userInfo.studentNumber,
                    loading = false
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    loading = false
                )
            }
            postSideEffect(HomeSideEffect.ToastError(it))
        }

    }

    fun search(name: String) = intent {
//        reduce {
//            state.copy(
//                loading = true
//            )
//        }
        lolSearchUseCase(
            param = LolSearchUseCase.Param(name)
        ).onSuccess { search ->
            reduce {
                state.copy(
                    loading = false,
                    lolName = search.name,
                    imageUrl = search.icon,
                    level = search.level
                )
            }

        }.onFailure {
            reduce {
                state.copy(
                    loading = false,
                    lolName = "",
                    imageUrl = "",
                    level = null
                )
            }
            Log.d(TAG, "search: ${it.message}")
        }

    }

    fun create(name: String) = intent {
        reduce {
            state.copy(
                loading = true
            )
        }
        lolCreateUseCase(
            LolCreateUseCase.Param(name)
        ).onSuccess {
            reduce {
                state.copy(
                    loading = false
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    loading = false
                )
            }
            postSideEffect(HomeSideEffect.FailCreateUser(it))
        }

    }

    fun inputNickname(name: String) = intent {
        reduce {
            state.copy(
                nickname = name
            )
        }
    }

    fun inputLoading(loading: Boolean) = intent {
        reduce {
            state.copy(
                loading = loading
            )
        }
    }
    fun input() = intent {
        reduce {
            state.copy(

            )
        }
    }

}