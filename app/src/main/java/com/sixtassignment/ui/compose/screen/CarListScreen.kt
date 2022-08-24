package com.sixtassignment.ui.compose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.sixtassginment.domain.entity.CarEntity
import com.sixtassignment.R

@Composable
fun ListBodyContent(
    response: List<CarEntity>?,
    isUpdatingContent: Boolean = false,
) {
    if(isUpdatingContent) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),


            ) {
            response?.let {
                itemsIndexed(response) { index: Int, carEntity: CarEntity ->
                    listItem(data = carEntity)

                }
            }
        }
    }else{
        Box(modifier = Modifier.fillMaxSize()){
            CircularProgressIndicator(modifier = Modifier.size(50.dp).width(50.dp))
        }
    }

}
@Composable
 fun listItem(data: CarEntity) {
    val textStyle = TextStyle(color = Color.Black, fontSize = 18.sp)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)

    )
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(4.dp)
    )
        {
            Card(
                shape = RoundedCornerShape(4.dp, 4.dp),
                elevation = 4.dp
            )
            {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp, 5.dp, 5.dp, 5.dp),
                        horizontalArrangement = Arrangement.Center,

                        verticalAlignment = Alignment.CenterVertically
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
                                .fillMaxSize()
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
                                .fillMaxSize()
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
                                .fillMaxSize()
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
                                .fillMaxSize()
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
                                .fillMaxSize()
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
                                .fillMaxSize()
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
                                .fillMaxSize()
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
                                .fillMaxSize()
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
                                .fillMaxSize()
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
                                .fillMaxSize()
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
            }


}
@Composable
fun getIconPainter(iconNameOrUrl: String): Painter {
    val errorIcon = R.drawable.ic_baseline_error_24
    return rememberImagePainter(
            request = ImageRequest.Builder(LocalContext.current)
                .size(100.dp.value.toInt())
                .placeholder(errorIcon)
                .data(iconNameOrUrl)
                .error(errorIcon)
                .build()
        )

}











