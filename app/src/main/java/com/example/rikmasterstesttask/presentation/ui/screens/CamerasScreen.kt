package com.example.rikmasterstesttask.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.rikmasterstesttask.R
import com.example.rikmasterstesttask.domain.model.CameraDto
import com.example.rikmasterstesttask.presentation.ui.components.CameraCard
import com.example.rikmasterstesttask.presentation.viewmodel.CameraViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CamerasScreen(
    cameraViewModel: CameraViewModel
) {
    val refreshScope = rememberCoroutineScope()
    val refreshing by cameraViewModel.refreshing.observeAsState(false)
    val state = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = {
            refreshScope.launch {
                cameraViewModel.getCameras()
            }
        }
    )
    val camerasUiState = cameraViewModel.camerasUiState.observeAsState()
    camerasUiState.value?.let { result ->
        if (result.isSuccess) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(state)
            ) {
                if (!refreshing) {
                    CameraCards(
                        cameras = result.getOrThrow(),
                        updateIsFavoriteAction = { id, isFavorite ->
                            cameraViewModel.update(id, isFavorite)
                        },
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.medium_padding))
                    )
                }
                PullRefreshIndicator(
                    refreshing = refreshing,
                    state = state,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }
}

@Composable
fun CameraCards(
    cameras: List<CameraDto>,
    modifier: Modifier = Modifier,
    updateIsFavoriteAction: (Long, Boolean) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_padding)),
        modifier = modifier
    ) {
        items(cameras) {
            CameraCard(
                cameraDto = it,
                updateIsFavoriteAction = updateIsFavoriteAction
            )
        }
    }
}