package com.sixtassignment.ui.compose.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.sixtassginment.domain.entity.CarEntity


@Composable
fun MainContent(
    response: List<CarEntity>? =null,
    isUpdatingContent: Boolean = false

) {
    val baseTitle = "" // stringResource(id = R.string.app_name)
    val (title, setTitle) = remember {
        mutableStateOf(baseTitle)
    }
    val listState = rememberLazyListState()
    val navController = rememberNavController()
    val isViewScrolled = remember {
        derivedStateOf {
            listState.firstVisibleItemScrollOffset > 5
        }
    }
    Scaffold(
        topBar = {
            HeaderContent(
                appBarTitle = title ,

                isViewScrolled = isViewScrolled,

            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(65.dp),
                backgroundColor = MaterialTheme.colors.background,
                elevation = 10.dp
            ) {
                BottomNavigationBar(navController = navController)
            }
        },content = { padding ->
            NavHostContainer(setTitle=setTitle,response=response,navController = navController, padding = padding,isUpdatingContent=isUpdatingContent)
        }
    )

}


