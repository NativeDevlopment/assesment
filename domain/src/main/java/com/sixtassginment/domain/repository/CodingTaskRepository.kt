package com.sixtassginment.domain.repository

import com.sixtassginment.domain.common.ResultState
import com.sixtassginment.domain.entity.CarEntity
import kotlinx.coroutines.flow.Flow

interface CodingTaskRepository {
   fun getCar(): Flow<ResultState<List<CarEntity>>>

}