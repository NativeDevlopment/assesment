package com.sixtassignment.ui.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.maps.android.compose.rememberCameraPositionState
import com.sixtassginment.domain.entity.CarEntity
import com.sixtassignment.R
import com.sixtassignment.ui.compose.BottomNavItems
import com.sixtassignment.ui.compose.screen.ListBodyContent


@Composable
fun ErrorBodyContent(msg: String, onSkipButtonClicked: () -> Unit) {

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_error_24),
                contentDescription = "Warning",
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = msg,
                style = TextStyle(
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Button(
                modifier = Modifier.padding(16.dp),
                onClick = onSkipButtonClicked
            ) {
                Text(text = "Proceed")
            }

        }

    }

}

@Composable
fun BodyContent(
    contentBody: List<CarEntity>,
    bottomContentPadding: Dp = 10.dp,
    listState: LazyListState = rememberLazyListState(),
    onPageLoadRequested: (pageIndex: Int?) -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(0.dp, 0.dp, 0.dp, 52.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        /*  ListBodyContent(response = contentBody) {
              
          }*/
            val cameraPositionState = rememberCameraPositionState {
            }
            GoogleMapView(response = contentBody, cameraPositionState = cameraPositionState)


        }

    }


}

@Composable
fun HeaderContent(
    appBarTitle: String? = null,

    isViewScrolled: State<Boolean> = mutableStateOf(false),

) {
    Column {
        TopAppBar(
            title = {
                Text(text = appBarTitle ?: "")
            },


            backgroundColor = MaterialTheme.colors.background,
            contentColor = Color.Black,
            elevation =  10.dp
        )

    }
}



@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation(

        // set background color
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    ) {

        // observe the backstack
        val navBackStackEntry  by navController.currentBackStackEntryAsState()

        // observe current route to change the icon
        // color,label color when navigated
        val currentRoute = navBackStackEntry?.destination?.route

        // Bottom nav items we declared
        BottomNavItems.forEach { navItem ->

            // Place the bottom nav items
            BottomNavigationItem(

                selectedContentColor= Color.Black,
                unselectedContentColor= Color.Black.copy(alpha = 0.4f),
                // it currentRoute is equal then its selected route
                selected = currentRoute == navItem.route,

                // navigate on click
                onClick = {
                    navController.navigate(navItem.route){
                        popUpTo(
                            navController.graph.startDestinationId
                        )
                        launchSingleTop = true
                    }
                },

                // Icon of navItem
                icon = {
                    Icon(
                         navItem.icon,
                        contentDescription = navItem.label

                    )
                },

                alwaysShowLabel = true
            )
        }
    }


    }
@Composable
fun NavHostContainer(
    setTitle: (String) -> Unit,
    response:List<CarEntity>?,
    navController: NavHostController,
    padding: PaddingValues,
    isUpdatingContent:Boolean =false,

) {

    NavHost(
        navController = navController,

        // set the start destination as home
        startDestination = "home",
        modifier = Modifier.padding(paddingValues = padding),

        builder = {

            composable("home") {
                setTitle("Home")

                GoogleMapView(response = response,isUpdatingContent=isUpdatingContent)
            }


            composable("list") {
                setTitle("Car List")
                ListBodyContent(response = response,isUpdatingContent=isUpdatingContent)
            }


        })
}


