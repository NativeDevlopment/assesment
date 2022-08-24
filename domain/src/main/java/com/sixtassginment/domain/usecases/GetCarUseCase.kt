package com.sixtassginment.domain.usecases

import com.sixtassginment.domain.common.ResultState
import com.sixtassginment.domain.entity.CarEntity
import kotlinx.coroutines.flow.Flow

interface GetCarUseCase {
     fun  excute (): Flow<ResultState<List<CarEntity>>>
}