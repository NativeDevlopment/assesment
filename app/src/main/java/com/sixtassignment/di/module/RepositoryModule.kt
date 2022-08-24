package com.sixtassignment.di.module

import com.sixtassginment.data.repository.CodingTaskRepositoryimpl
import com.sixtassginment.domain.repository.CodingTaskRepository
import org.koin.dsl.module

val repositoryModule = module {

    single <CodingTaskRepository> {

        CodingTaskRepositoryimpl(get())
    }

}