package com.hu.dgswgr.remote.utiles

import android.util.Log
import com.google.gson.Gson
import com.hu.dgswgr.remote.response.ErrorResponse
import com.hu.dgswgr.domain.exception.BadRequestException
import com.hu.dgswgr.domain.exception.ConflictException
import com.hu.dgswgr.domain.exception.ExpiredRefreshTokenException
import com.hu.dgswgr.domain.exception.ForbiddenException
import com.hu.dgswgr.domain.exception.NoConnectivityException
import com.hu.dgswgr.domain.exception.NoInternetException
import com.hu.dgswgr.domain.exception.NotFoundException
import com.hu.dgswgr.domain.exception.OtherHttpException
import com.hu.dgswgr.domain.exception.ServerException
import com.hu.dgswgr.domain.exception.TimeOutException
import com.hu.dgswgr.domain.exception.TooManyRequestsException
import com.hu.dgswgr.domain.exception.UnAuthorizedException
import com.hu.dgswgr.domain.exception.UnknownException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

const val EXPIRED_TOKEN_MESSAGE = "만료된 토큰"

suspend inline fun <T> dgswgrApiCall(
    crossinline callFunction: suspend () -> T,
): T {
    return try {
        withContext(Dispatchers.IO) {
            callFunction.invoke()
        }
    } catch (e: HttpException) {
        val message: String = getErrorMessage(e)

        throw when (e.code()) {
            400 -> BadRequestException(
                message = message,
                fieldErrors = emptyList()
            )
            401 -> {
                if (e.message == EXPIRED_TOKEN_MESSAGE) {
                    ExpiredRefreshTokenException()
                } else {
                    UnAuthorizedException(
                        message = message
                    )
                }
            }
            403 -> ForbiddenException(
                message = message
            )

            404 -> NotFoundException(
                message = message,
            )
            408 -> TimeOutException(
                message = message
            )
            409 -> ConflictException(
                message = message
            )
            429 -> TooManyRequestsException(
                message = message,
            )
            500, 501, 502, 503 -> ServerException(
                message = message,
            )
            else -> OtherHttpException(
                code = e.code(),
                message = message,
            )
        }
    } catch (e: UnknownHostException) {
        throw NoInternetException()
    } catch (e: SocketTimeoutException) {
        throw TimeOutException(
            message = e.message,
        )
    } catch (e: ExpiredRefreshTokenException) {
        throw e
    } catch (e: NoInternetException) {
        throw NoInternetException()
    } catch (e: NoConnectivityException) {
        throw NoConnectivityException()
    } catch (e: Exception) {
        Log.d("LOG", "dgswgrApiCall: $e")
        throw UnknownException(
            message = e.message,
        )
    }
}

private const val UNKNOWN_ERROR_MESSAGE = "알 수 없는 오류가 발생했습니다."

fun getErrorMessage(exception: HttpException): String {
    val errorStr = exception.response()?.errorBody()?.string()
    val errorDto: ErrorResponse? = Gson().fromJson(
        errorStr, ErrorResponse::class.java
    )

    return errorDto?.message ?: UNKNOWN_ERROR_MESSAGE
}
