package com.example.rikmasterstesttask.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rikmasterstesttask.presentation.ui.screens.CamerasScreen
import com.example.rikmasterstesttask.presentation.ui.screens.DoorsScreen
import com.example.rikmasterstesttask.presentation.viewmodel.CameraViewModel
import com.example.rikmasterstesttask.presentation.viewmodel.DoorViewModel

@Composable
fun TestTaskNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CamerasScreen.route,
        modifier = modifier
    ) {
        composable(Screen.CamerasScreen.route) {
            val cameraViewModel = hiltViewModel<CameraViewModel>()
            CamerasScreen(cameraViewModel)
        }
        composable(Screen.DoorsScreen.route) {
            val doorViewModel = hiltViewModel<DoorViewModel>()
            DoorsScreen(doorViewModel)
        }
    }
}

fun NavController.navigateSingleTopTo(route: String) =
    navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }