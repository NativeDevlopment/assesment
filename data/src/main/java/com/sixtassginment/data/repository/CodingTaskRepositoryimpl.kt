package com.sixtassginment.data.repository

import android.util.Log
import com.sixtassginment.data.api.ICodingTaskApi
import com.sixtassginment.data.mapper.dtotoentity.map
import com.sixtassginment.domain.common.ResultState
import com.sixtassginment.domain.entity.CarEntity
import com.sixtassginment.domain.repository.CodingTaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import java.net.UnknownHostException

class CodingTaskRepositoryimpl (val api: ICodingTaskApi) :CodingTaskRepository {

    override fun getCar(): Flow<ResultState<List<CarEntity>>> {
        Log.e("frepository>>",">>>.")

        return flow {
            emit(ResultState.loading(null))
          emit( ResultState.success( remoteCarListCall()))
        }.catch { e ->
            when (e) {
                is SocketException -> {
                    emit(
                        ResultState.error(
                            "Request timeout. Please check your internet connection.",
                            null
                        )
                    )
                }
                is HttpException -> {
                    val httpCode = e.code()
                    emit(
                        ResultState.error(
                            when (httpCode) {
                                401 -> "User Session Expired! Please logout and re-login to your account."
                                else -> "Oops..! Something went wrong!\nPlease try again after sometime.\n(Error Code: $httpCode)"
                            },
                            null
                        )
                    )
                }
                is UnknownHostException, is IOException -> {
                    emit(
                        ResultState.error(
                            "Unable to reach server. Please check your internet connection.",
                            null
                        )
                    )
                }
                else -> {
                    emit(
                        ResultState.error(
                            "Unable to understand server's response. Please try again after sometime.",
                            null
                        )
                    )
                }
            }
        }
    }

   suspend  fun remoteCarListCall(): List<CarEntity> {
       return api.getCars().map {it.map()}

    }
}