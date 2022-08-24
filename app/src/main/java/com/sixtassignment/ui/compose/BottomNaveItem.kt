package com.sixtassignment.ui.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector


data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val BottomNavItems = listOf(
    BottomNavItem(
        label = "Home",
        icon = Icons.Filled.Home,
        route = "home"
    ),
    BottomNavItem(
        label = "List",
        icon = Icons.Filled.List,
        route = "list"
    ),


)