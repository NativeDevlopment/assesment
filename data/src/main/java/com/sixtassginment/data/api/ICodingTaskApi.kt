package com.sixtassginment.data.api

import com.sixtassginment.data.dto.CarDto
import retrofit2.http.GET

interface ICodingTaskApi {
    @GET("codingtask/cars")
    suspend fun getCars():List<CarDto>

}
