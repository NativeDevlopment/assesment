package com.sixtassignment.ui.compose

import androidx.lifecycle.*
import com.sixtassginment.domain.common.ResultState
import com.sixtassginment.domain.entity.CarEntity
import com.sixtassginment.domain.usecases.GetCarUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AssignmentViewModel (val getCarUseCase: GetCarUseCase) :ViewModel() {
     val carListDataLiveData = MutableLiveData<ResultState<List<CarEntity>>>()

    fun getCarsList() {
        viewModelScope.launch(Dispatchers.IO) {
           getCarUseCase.excute().collect{carListDataLiveData.postValue(it)}

        }


    }
}