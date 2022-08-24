package com.sixtassginment.data.mapper.dtotoentity

import com.sixtassginment.data.dto.CarDto
import com.sixtassginment.domain.entity.CarEntity

fun CarDto.map()= CarEntity(
    id=id,
    modelIdentifier= modelIdentifier,
    modelName= modelName,
   name= name,
   make= make,
   group= group,
    color=color,
    series=series,
    fuelType=fuelType,
    fuelLevel=fuelLevel,
    transmission=transmission,
    licensePlate=licensePlate,
    latitude=latitude,
    longitude=longitude,
    innerCleanliness=innerCleanliness,
    carImageUrl=carImageUrl
)