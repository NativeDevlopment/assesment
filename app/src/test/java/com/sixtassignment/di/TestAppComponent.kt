package com.sixtassignment.di

import com.sixtassignment.di.MockWebServerDIPTest
import com.sixtassignment.di.configureNetworkModuleForTest
import com.sixtassignment.di.module.apiModule
import com.sixtassignment.di.module.repositoryModule
import com.sixtassignment.di.module.useCaseModule


fun configureTestAppComponent(baseApi: String) = listOf(
    MockWebServerDIPTest,
    configureNetworkModuleForTest(baseApi),
    apiModule,

    useCaseModule,
    repositoryModule

)
