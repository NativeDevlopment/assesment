package com.sixtassignment.di.module

import com.sixtassignment.ui.compose.AssignmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AssignmentViewModel(get())
    }

}