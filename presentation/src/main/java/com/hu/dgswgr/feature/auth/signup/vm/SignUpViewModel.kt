package com.hu.dgswgr.feature.auth.signup.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hu.dgswgr.domain.usecase.auth.CheckUseCase
import com.hu.dgswgr.domain.usecase.auth.LoginUseCase
import com.hu.dgswgr.feature.auth.signup.mvi.SignUpSideEffect
import com.hu.dgswgr.feature.auth.signup.mvi.SignUpState
import com.hu.dgswgr.domain.usecase.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val checkUseCase: CheckUseCase,
    private val loginUseCase: LoginUseCase
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

    fun checkId(
        loginId: String
    ) = intent {
        reduce {
            state.copy(loading = true)
        }
        checkUseCase(
            param = CheckUseCase.Param(
                loginId = loginId
            )
        ).onSuccess {
            reduce {
                state.copy(
                    loading = false,
                )
            }
            postSideEffect(SignUpSideEffect.SuccessCheck)
        }.onFailure {
            reduce {
                state.copy(
                    loading = false,
                )
            }
            postSideEffect(SignUpSideEffect.FailCheck(it))
        }
    }

    fun login(
        loginId: String,
        password: String,
    ) = intent {
        reduce {
            state.copy(loading = true)
        }
        loginUseCase(
            param = LoginUseCase.Param(
                loginId = loginId,
                password = password
            )
        ).onSuccess {
            reduce {
                state.copy(loading = false)
            }
            postSideEffect(SignUpSideEffect.SuccessLogin)
        }.onFailure {
            reduce {
                state.copy(loading = false)
            }
            Log.d("LOG", "login: ${it.message}")
            postSideEffect(SignUpSideEffect.FailLogin(it))
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
    fun inputSavePassword(save: Boolean) = intent {
        reduce {
            state.copy(saveLogin = save)
        }
    }
    fun testInputLoading(loading: Boolean) = intent {
        reduce {
            state.copy(loading = loading)
        }
    }
    fun resetPage() = intent {
        reduce {
            state.copy(
                loginId = "",
                password = "",
                name = "",
                grade = "",
                classNumber = "",
                studentNumber = "",
                page = 0,
                loading = false
            )
        }
    }


}