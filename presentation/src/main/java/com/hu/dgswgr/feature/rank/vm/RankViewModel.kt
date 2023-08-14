package com.hu.dgswgr.feature.rank.vm

import androidx.lifecycle.ViewModel
import com.hu.dgswgr.domain.usecase.lol.LolRankUseCase
import com.hu.dgswgr.feature.home.mvi.HomeSideEffect
import com.hu.dgswgr.feature.home.mvi.HomeState
import com.hu.dgswgr.feature.rank.mvi.RankSideEffect
import com.hu.dgswgr.feature.rank.mvi.RankState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class RankViewModel @Inject constructor(
    private val lolRankUseCase: LolRankUseCase
): ContainerHost<RankState, RankSideEffect>, ViewModel() {
    override val container = container<RankState, RankSideEffect>(RankState())

    fun load(
        category: String
    ) = intent {
        reduce {
            state.copy(
                loading = true,
                firstTime = false
            )
        }
        lolRankUseCase(
            LolRankUseCase.Param(category = category)
        ).onSuccess { rankList ->
            reduce {
                state.copy(
                    list = rankList,
                    loading = false
                )
            }
        }.onFailure {
            postSideEffect(RankSideEffect.ToastError(it))
        }
    }

//    fun inputCategory(category: String) = intent {
//        reduce {
//            state.copy(
//                category = category
//            )
//        }
//    }

}