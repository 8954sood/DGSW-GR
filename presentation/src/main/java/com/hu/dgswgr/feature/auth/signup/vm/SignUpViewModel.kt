package com.hu.dgswgr.feature.auth.signup.vm

import androidx.lifecycle.ViewModel
import com.hu.dgswgr.feature.auth.signup.mvi.SignUpSideEffect
import com.hu.dgswgr.feature.auth.signup.mvi.SignUpState
import com.hu.domain.usecase.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
): ContainerHost<SignUpState, SignUpSideEffect>, ViewModel() {

    override val container = container<SignUpState, SignUpSideEffect>(SignUpState())

    fun signUp(
        loginId: String,
        password: String,
        name: String,
        grade: String,
        classNumber: String,
        studentNumber: String
    ) = intent {
        reduce {
            state.copy(loading = true)
        }
        signUpUseCase(
            param = SignUpUseCase.Param(
                loginId = loginId,
                password = password,
                name = name,
                grade = grade,
                classNumber = classNumber,
                studentNumber = studentNumber
            )
        ).onSuccess {
            reduce {
                state.copy(
                    loading = false,
                )
            }
            postSideEffect(SignUpSideEffect.SuccessSignUp)
        }.onFailure {
            reduce { state.copy(
                loading = false,
                )
            }
            postSideEffect(SignUpSideEffect.FailSignUp(it))
        }
    }

    fun inputLoginId(text: String) = intent {
        reduce {
            state.copy(loginId = text)
        }
    }

    fun inputPassword(text: String) = intent {
        reduce {
            state.copy(password = text)
        }
    }
    fun inputName(text: String) = intent {
        reduce {
            state.copy(name = text)
        }
    }
    fun inputGrade(grade: String) = intent {
        reduce {
            state.copy(grade = grade)
        }
    }
    fun inputClassNumber(number: String) = intent {
        reduce {
            state.copy(classNumber = number)
        }
    }
    fun inputStudentNumber(number: String) = intent {
        reduce {
            state.copy(studentNumber = number)
        }
    }
    fun setPage(page: Int) = intent {
        reduce {
            state.copy(page = page)
        }
    }
    fun testInputLoading(loading: Boolean) = intent {
        reduce {
            state.copy(loading = loading)
        }
    }


}