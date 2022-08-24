package com.sixtassignment.ui.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.lifecycle.Observer
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.sixtassginment.domain.common.Status
import com.sixtassignment.ui.compose.components.ErrorBodyContent
import com.sixtassignment.ui.compose.components.MainContent
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val singapore = LatLng(1.35, 103.87)
    private val singapore2 = LatLng(1.40, 103.77)
    private val singapore3 = LatLng(1.45, 103.77)
    private val defaultCameraPosition = CameraPosition.fromLatLngZoom(singapore, 11f)
    val viewModel: AssignmentViewModel  by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCarsList()
        viewModel.carListDataLiveData.observe(this, Observer {
            it.let {
                when (it.status) {
                    Status.LOADING->{
                        setContent { MainContent( isUpdatingContent=false ) }
                    }

                    Status.SUCCESS -> {
                        it?.data.let { data ->

                            if (data != null) {
                                setContent {
                                    MainContent(data,  true)
                                }
                            }

                        }
                    }
                    Status.ERROR->{
                        it?.message?.let { it1 ->
                         setContent {
                             ErrorBodyContent(msg = it1, onSkipButtonClicked = {
                                 viewModel.getCarsList()
                             })
                         }

                        }
                    }
                }

/*
                setContent {

                    Box(Modifier.fillMaxSize()) {


                    }
                    setContent {
                        var isMapLoaded by remember { mutableStateOf(false) }
                        // Observing and controlling the camera's state can be done with a CameraPositionState
                        val cameraPositionState = rememberCameraPositionState {
                            position = defaultCameraPosition
                        }

                        Box(Modifier.fillMaxSize()) {


                            GoogleMapView(
                                modifier = Modifier.fillMaxSize(),
                                cameraPositionState = cameraPositionState,
                                onMapLoaded = {
                                    isMapLoaded = true
                                },
                            )
                            if (!isMapLoaded) {

                                AnimatedVisibility(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    visible = !isMapLoaded,
                                    enter = EnterTransition.None,
                                    exit = fadeOut()
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .background(MaterialTheme.colors.background)
                                            .wrapContentSize()
                                    )
                                }
                            }
                        }

                    }
                }
*/
            }


        })

}
}


