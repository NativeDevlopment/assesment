package com.sixtassginment.domain.usecases

import android.util.Log
import com.sixtassginment.domain.common.ResultState
import com.sixtassginment.domain.entity.CarEntity
import com.sixtassginment.domain.repository.CodingTaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetCarUseCaseImpl ( val repository: CodingTaskRepository) :GetCarUseCase{
    override  fun excute(): Flow<ResultState<List<CarEntity>>> = flow<ResultState<List<CarEntity>>> {
        Log.e("flow>>>",">>>.")
        repository.getCar().collect{emit(it)}
        }

        . catch { e ->
            emit(ResultState.error("Couldn't understand servers response. Please try later.", null))
        }.flowOn(Dispatchers.IO)
    }

