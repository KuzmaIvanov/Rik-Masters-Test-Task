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
import com.example.rikmasterstesttask.domain.model.DoorDto
import com.example.rikmasterstesttask.presentation.ui.components.DoorCard
import com.example.rikmasterstesttask.presentation.viewmodel.DoorViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DoorsScreen(
    doorViewModel: DoorViewModel
) {
    val refreshScope = rememberCoroutineScope()
    val refreshing by doorViewModel.refreshing.observeAsState(false)
    val state = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = {
            refreshScope.launch {
                doorViewModel.getDoors()
            }
        }
    )
    val doorsUiState = doorViewModel.doorUiState.observeAsState()
    doorsUiState.value?.let { result ->
        if (result.isSuccess) {
            Box(modifier = Modifier
                .fillMaxSize()
                .pullRefresh(state)
            ) {
                if(!refreshing) {
                    DoorsCard(
                        doors = result.getOrThrow(),
                        updateIsFavoriteAction = { id, isFavorite ->
                            doorViewModel.updateIsFavorite(id, isFavorite)
                        },
                        updateDoorNameAction = { id, name ->
                            doorViewModel.updateName(id, name)
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
fun DoorsCard(
    doors: List<DoorDto>,
    modifier: Modifier = Modifier,
    updateIsFavoriteAction: (Long, Boolean) -> Unit,
    updateDoorNameAction: (Long, String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_padding)),
    ) {
        items(doors) {
            DoorCard(
                doorDto = it,
                updateIsFavoriteAction = updateIsFavoriteAction,
                updateDoorNameAction = updateDoorNameAction
            )
        }
    }
}