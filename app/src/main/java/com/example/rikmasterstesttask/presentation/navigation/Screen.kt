package com.example.rikmasterstesttask.presentation.navigation

import androidx.annotation.StringRes
import com.example.rikmasterstesttask.R

sealed class Screen (
    val route: String,
    @StringRes val resourceId: Int
) {
    object CamerasScreen: Screen("Cameras", R.string.cameras)
    object DoorsScreen: Screen("Doors", R.string.doors)
}