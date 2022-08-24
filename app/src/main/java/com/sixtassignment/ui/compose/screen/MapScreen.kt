package com.sixtassignment.ui.compose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*
import com.sixtassginment.domain.entity.CarEntity
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GoogleMapView(
    response:List<CarEntity>?,
    modifier: Modifier = Modifier,
    isUpdatingContent:Boolean = false,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    onMapLoaded: () -> Unit = {},
    content: @Composable () -> Unit = {}

) {
      val TAG = "BasicMapActivity"



    var uiSettings by remember { mutableStateOf(MapUiSettings(compassEnabled = false, zoomControlsEnabled = false)) }
    var builder = LatLngBounds.Builder()
    if(isUpdatingContent) {
        response?.forEach { car ->
            val carlocation = car.latitude?.let { car.longitude?.let { it1 -> LatLng(it, it1) } }
            if (carlocation != null) {
                builder.include(carlocation)
            }
        }

    }


    var mapProperties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }
    val  (uiDataState,setUiDataState) = remember {
        mutableStateOf(CarEntity(id="No data"))
    }
    val sheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {

                   bottomSheetData(uiDataState)

        }
    ) {

        GoogleMap(

            modifier = modifier,
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = uiSettings,
            onMapLoaded = onMapLoaded,
            onPOIClick = {
            }
        ) {

            if(isUpdatingContent ){
               cameraPositionState.move(CameraUpdateFactory.newLatLngBounds(builder.build(), 64))

                response?.forEachIndexed { index, carEntity ->
                val markerClick: (Marker) -> Boolean = {
                    setUiDataState(carEntity)
                    scope.launch {   sheetState.show()}

                    cameraPositionState.projection?.let { projection ->

                    }
                    false
                }
                var state = carEntity.latitude?.let { latitute ->
                    carEntity.longitude?.let { longitude ->
                        LatLng(
                            latitute,
                            longitude
                        )
                    }
                }
                    ?.let { rememberMarkerState(position = it) }
                if (state != null) {


                    MarkerInfoWindowContent(
                        state = state,
                        title = carEntity.name,
                        icon = BitmapDescriptorFactory.defaultMarker(),
                        onClick = markerClick,
                        snippet = "Model Name : " + carEntity.modelName

                    ) {

                        Column(
                            modifier = Modifier
                                .width(200.dp)
                                .height(80.dp)
                        ) {

                            Text("Car name : ".plus(it.title ?: "Title"), color = Color.Blue)
                            Text(it.snippet ?: "Title", color = Color.Blue)
                        }
                    }
                }

            }}




            content()
        }

    }
}




    @Composable
    fun bottomSheetData(data: CarEntity) {
        val textStyle = TextStyle(color = Color.Black, fontSize = 18.sp)



                Column(modifier = Modifier.fillMaxWidth().wrapContentSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth().size(150.dp)
                            .padding(5.dp, 5.dp, 5.dp, 5.dp),
                        horizontalArrangement = Arrangement.Center,

                        verticalAlignment = Alignment.Top
                    ) {
                        data.carImageUrl?.let {

                            Image(
                                painter = getIconPainter(it),
                                contentDescription = "",
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(100.dp)

                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth().wrapContentSize()
                            .padding(5.dp, 5.dp, 5.dp, 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        verticalAlignment = Alignment.CenterVertically
                    )
                    {

                        Text(
                            textAlign = TextAlign.Left,
                            text = "Name",
                            modifier = Modifier
                                .weight(1f, true)
                                .padding(horizontal = 5.dp, vertical = 5.dp),
                            style = textStyle

                        )
                        data.name?.let {
                            Text(
                                textAlign = TextAlign.Left,
                                text = it,
                                modifier = Modifier
                                    .weight(1f, true)
                                    .padding(horizontal = 5.dp, vertical = 5.dp),
                                style = textStyle

                            )
                        }


                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth().wrapContentSize()
                            .padding(5.dp, 5.dp, 5.dp, 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        verticalAlignment = Alignment.CenterVertically
                    )
                    {

                        Text(
                            textAlign = TextAlign.Left,
                            text = "Licenece Number",
                            modifier = Modifier
                                .weight(1f, true)
                                .padding(horizontal = 5.dp, vertical = 5.dp),
                            style = textStyle

                        )
                        data.licensePlate?.let {
                            Text(
                                textAlign = TextAlign.Left,
                                text = it,
                                modifier = Modifier
                                    .weight(1f, true)
                                    .padding(horizontal = 5.dp, vertical = 5.dp),
                                style = textStyle

                            )
                        }


                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth().wrapContentSize()
                            .padding(5.dp, 5.dp, 5.dp, 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        verticalAlignment = Alignment.CenterVertically
                    )
                    {

                        Text(
                            textAlign = TextAlign.Left,
                            text = "Model Name",
                            modifier = Modifier
                                .weight(1f, true)
                                .padding(horizontal = 5.dp, vertical = 5.dp),
                            style = textStyle

                        )
                        data.modelName?.let {
                            Text(
                                textAlign = TextAlign.Left,
                                text = it,
                                modifier = Modifier
                                    .weight(1f, true)
                                    .padding(horizontal = 5.dp, vertical = 5.dp),
                                style = textStyle

                            )
                        }


                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth().wrapContentSize()
                            .padding(5.dp, 5.dp, 5.dp, 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        verticalAlignment = Alignment.CenterVertically
                    )
                    {

                        Text(
                            textAlign = TextAlign.Left,
                            text = "Color",
                            modifier = Modifier
                                .weight(1f, true)
                                .padding(horizontal = 5.dp, vertical = 5.dp),
                            style = textStyle

                        )
                        data.color?.let {
                            Text(
                                textAlign = TextAlign.Left,
                                text = it,
                                modifier = Modifier
                                    .weight(1f, true)
                                    .padding(horizontal = 5.dp, vertical = 5.dp),
                                style = textStyle

                            )
                        }


                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth().wrapContentSize()
                            .padding(5.dp, 5.dp, 5.dp, 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        verticalAlignment = Alignment.CenterVertically
                    )
                    {

                        Text(
                            textAlign = TextAlign.Left,
                            text = "Series",
                            modifier = Modifier
                                .weight(1f, true)
                                .padding(horizontal = 5.dp, vertical = 5.dp),
                            style = textStyle

                        )
                        data.series?.let {
                            Text(
                                textAlign = TextAlign.Left,
                                text = it,
                                modifier = Modifier
                                    .weight(1f, true)
                                    .padding(horizontal = 5.dp, vertical = 5.dp),
                                style = textStyle

                            )
                        }


                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth().wrapContentSize()
                            .padding(5.dp, 5.dp, 5.dp, 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        verticalAlignment = Alignment.CenterVertically
                    )
                    {

                        Text(
                            textAlign = TextAlign.Left,
                            text = "Transmission",
                            modifier = Modifier
                                .weight(1f, true)
                                .padding(horizontal = 5.dp, vertical = 5.dp),
                            style = textStyle

                        )
                        data.transmission?.let {
                            Text(
                                textAlign = TextAlign.Left,
                                text = it,
                                modifier = Modifier
                                    .weight(1f, true)
                                    .padding(horizontal = 5.dp, vertical = 5.dp),
                                style = textStyle

                            )
                        }


                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth().wrapContentSize()
                            .padding(5.dp, 5.dp, 5.dp, 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        verticalAlignment = Alignment.CenterVertically
                    )
                    {

                        Text(
                            textAlign = TextAlign.Left,
                            text = "Fuel Level",
                            modifier = Modifier
                                .weight(1f, true)
                                .padding(horizontal = 5.dp, vertical = 5.dp),
                            style = textStyle

                        )
                        data.fuelLevel?.let {
                            Text(
                                textAlign = TextAlign.Left,
                                text = it.toString(),
                                modifier = Modifier
                                    .weight(1f, true)
                                    .padding(horizontal = 5.dp, vertical = 5.dp),
                                style = textStyle
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth().wrapContentSize()
                            .padding(5.dp, 5.dp, 5.dp, 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        verticalAlignment = Alignment.CenterVertically
                    )
                    {

                        Text(
                            textAlign = TextAlign.Left,
                            text = "Group",
                            modifier = Modifier
                                .weight(1f, true)
                                .padding(horizontal = 5.dp, vertical = 5.dp),
                            style = textStyle

                        )
                        data.group?.let {
                            Text(
                                textAlign = TextAlign.Left,
                                text = it,
                                modifier = Modifier
                                    .weight(1f, true)
                                    .padding(horizontal = 5.dp, vertical = 5.dp),
                                style = textStyle

                            )
                        }


                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth().wrapContentSize()
                            .padding(5.dp, 5.dp, 5.dp, 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        verticalAlignment = Alignment.CenterVertically
                    )
                    {

                        Text(
                            textAlign = TextAlign.Left,
                            text = "Make",
                            modifier = Modifier
                                .weight(1f, true)
                                .padding(horizontal = 5.dp, vertical = 5.dp),
                            style = textStyle

                        )
                        data.make?.let {
                            Text(
                                textAlign = TextAlign.Left,
                                text = it,
                                modifier = Modifier
                                    .weight(1f, true)
                                    .padding(horizontal = 5.dp, vertical = 5.dp),
                                style = textStyle

                            )
                        }


                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth().wrapContentSize()
                            .padding(5.dp, 5.dp, 5.dp, 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        verticalAlignment = Alignment.CenterVertically
                    )
                    {

                        Text(
                            textAlign = TextAlign.Left,
                            text = "Model Identifier",
                            modifier = Modifier
                                .weight(1f, true)
                                .padding(horizontal = 5.dp, vertical = 5.dp),
                            style = textStyle

                        )
                        data.modelIdentifier?.let {
                            Text(
                                textAlign = TextAlign.Left,
                                text = it,
                                modifier = Modifier
                                    .weight(1f, true)
                                    .padding(horizontal = 5.dp, vertical = 5.dp),
                                style = textStyle

                            )
                        }


                    }
                }





}


