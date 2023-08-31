package com.example.rikmasterstesttask.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.rikmasterstesttask.R
import com.example.rikmasterstesttask.domain.model.DoorDto
import com.example.rikmasterstesttask.presentation.ui.theme.BluePrimary
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DoorCard(
    doorDto: DoorDto,
    updateIsFavoriteAction: (Long, Boolean) -> Unit,
    updateDoorNameAction: (Long, String) -> Unit
) {
    var isFavorite by rememberSaveable {
        mutableStateOf(doorDto.isFavorite)
    }
    var doorName by rememberSaveable {
        mutableStateOf(doorDto.name)
    }
    var openDialog by rememberSaveable { mutableStateOf(false) }
    RevealSwipe(
        maxRevealDp = dimensionResource(id = R.dimen.door_swipe_max_reveal),
        modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.small_padding)),
        directions = setOf(
            RevealDirection.EndToStart
        ),
        hiddenContentEnd = {
            FloatingActionButton(
                onClick = {
                    openDialog = true
                },
                shape = RoundedCornerShape(100),
                containerColor = Color.White,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.reveal_swipe_icon_size))
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null,
                    tint = BluePrimary
                )
            }
            FloatingActionButton(
                onClick = {
                    isFavorite = !isFavorite
                    updateIsFavoriteAction(doorDto.id, isFavorite)
                },
                shape = RoundedCornerShape(100),
                containerColor = Color.White,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.reveal_swipe_icon_horizontal_padding))
                    .size(dimensionResource(id = R.dimen.reveal_swipe_icon_size))
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = null,
                    tint = Color.Yellow
                )
            }
        },
        backgroundCardEndColor = Color.White
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box {
                if (doorDto.snapshot != null) {
                    AsyncImage(
                        model = doorDto.snapshot,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.card_snapshot_image))
                    )
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(dimensionResource(id = R.dimen.play_arrow_size)),
                        tint = Color.White
                    )
                    if (isFavorite) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(dimensionResource(id = R.dimen.small_padding)),
                            tint = Color.Yellow
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.medium_padding)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = doorName
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.BottomEnd),
                        tint = BluePrimary
                    )
                }
            }
        }
    }
    if (openDialog) {
        EditDoorNameDialog(
            enteredDoorNameInitialValue = doorName,
            onConfirmButtonClick = {
                doorName = it
                updateDoorNameAction(doorDto.id, it)
                openDialog = false
            },
            onDismissButtonClick = {
                openDialog = false
            }
        )
    }
}