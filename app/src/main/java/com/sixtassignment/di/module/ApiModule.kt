package com.sixtassignment.di.module

import com.sixtassginment.data.api.ICodingTaskApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module{
    single {
        get<Retrofit>().create(ICodingTaskApi::class.java)
    }
}