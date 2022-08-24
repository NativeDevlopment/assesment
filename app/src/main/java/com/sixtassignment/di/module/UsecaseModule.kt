package com.sixtassignment.di.module

import com.sixtassginment.domain.usecases.GetCarUseCase
import com.sixtassginment.domain.usecases.GetCarUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetCarUseCase> {
        GetCarUseCaseImpl(repository = get())
    }
}