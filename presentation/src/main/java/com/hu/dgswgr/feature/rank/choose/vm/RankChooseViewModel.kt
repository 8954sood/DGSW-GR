package com.hu.dgswgr.feature.rank.choose.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hu.dgswgr.domain.usecase.lol.LolInfoUseCase
import com.hu.dgswgr.feature.rank.choose.mvi.RankChooseSideEffect
import com.hu.dgswgr.feature.rank.choose.mvi.RankChooseState
import com.hu.dgswgr.root.main.view.MainActivity.Companion.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class RankChooseViewModel @Inject constructor(
    private val lolInfoUseCase: LolInfoUseCase
): ContainerHost<RankChooseState, RankChooseSideEffect>, ViewModel() {

    override val container = container<RankChooseState, RankChooseSideEffect>(RankChooseState())

    fun load(id: Int) = intent {
        reduce {
            state.copy(
                loading = true
            )
        }
        lolInfoUseCase(
            LolInfoUseCase.Param(
                id = id
            )
        ).onSuccess {
            Log.d(TAG, "load: $it")
            reduce {
                state.copy(
                    classId = it.classId,
                    grade = it.grade,
                    icon = it.icon,
                    level = it.level,
                    most = it.most,
                    name = it.name,
                    nickname = it.nickname,
                    number = it.number,
                    tierIcon = it.tierIcon,
                    tierPoint = it.tierPoint,
                    tierStr = it.tierStr,
                    winLose = it.winLose,
                    winRate = it.winRate,
                    loading = false
                )
            }
        }.onFailure {
            Log.d(TAG, "load: $it")
            reduce {
                state.copy(
                    loading = false
                )
            }
            postSideEffect(RankChooseSideEffect.ToastError(it))
        }
    }

}