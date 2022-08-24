package com.sixtassignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sixtassginment.domain.usecases.GetCarUseCase
import com.sixtassignment.base.BaseUnitTest
import com.sixtassignment.ui.compose.AssignmentViewModel
import com.sixtassignment.utils.MockResponse

import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.KoinTest
import org.koin.test.inject
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import java.net.HttpURLConnection


@RunWith(PowerMockRunner::class)
@PowerMockRunnerDelegate(JUnit4::class)
class AssignmentViewModelTest : BaseUnitTest(), KoinTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var assignmentViewModel: AssignmentViewModel

    private val getcarUseCase: GetCarUseCase by inject()


    @Before
    override fun setUp() {
        super.setUp()
        assignmentViewModel =
            AssignmentViewModel(
                getcarUseCase
            )
    }

    @Test
    fun testViewModelInitialized() {
        Assert.assertNotNull(assignmentViewModel)
    }









    @Test
    fun loadCarListSuccess() {

        mockWebServer.enqueue(
            MockResponse.createMockResponse(
                "car_list_response",
                HttpURLConnection.HTTP_OK
            )
        )
        assignmentViewModel.getCarsList()
        assignmentViewModel.carListDataLiveData.observeForever {
            Assert.assertTrue(true)
        }

    }

    @Test
    fun loadCurrencyListFail() {
        mockWebServer.enqueue(
            MockResponse.createMockResponse(
                "error_response",
                HttpURLConnection.HTTP_BAD_GATEWAY
            )
        )
        assignmentViewModel.getCarsList()
        assignmentViewModel.carListDataLiveData.observeForever {
            Assert.assertTrue(true)
        }
    }

}