package com.example.rikmasterstesttask.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.rikmasterstesttask.R
import com.example.rikmasterstesttask.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestTaskTopAppBar(
    onTabSelected: (String) -> Unit
) {
    var state by rememberSaveable { mutableStateOf(0) }
    val screens = listOf(
        Screen.CamerasScreen,
        Screen.DoorsScreen
    )
    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(text = stringResource(id = R.string.my_home))
            }
        )
        TabRow(selectedTabIndex = state) {
            screens.forEachIndexed { index, screen ->
                Tab(
                    selected = state == index,
                    onClick = {
                        state = index
                        onTabSelected(screen.route)
                    },
                    text = {
                        Text(
                            text = stringResource(id = screen.resourceId),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }
        }
    }
}