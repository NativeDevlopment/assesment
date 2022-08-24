package com.sixtassignment.di.appcomponents

import com.sixtassignment.di.module.*
import org.koin.core.module.Module

val appComponent: List<Module> = listOf(
     retrofitModule,
    okhttpModule, repositoryModule, useCaseModule,
     apiModule, viewModelModule
)