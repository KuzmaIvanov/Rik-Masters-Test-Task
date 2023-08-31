package com.example.rikmasterstesttask.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
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
import com.example.rikmasterstesttask.domain.model.CameraDto
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CameraCard(
    cameraDto: CameraDto,
    updateIsFavoriteAction: (Long, Boolean) -> Unit
) {
    var isFavorite by rememberSaveable {
        mutableStateOf(cameraDto.isFavorite)
    }
    RevealSwipe(
        maxRevealDp = dimensionResource(id = R.dimen.camera_swipe_max_reveal),
        modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.small_padding)),
        directions = setOf(
            RevealDirection.EndToStart
        ),
        hiddenContentEnd = {
            FloatingActionButton(
                onClick = {
                    isFavorite = !isFavorite
                    updateIsFavoriteAction(cameraDto.id, isFavorite)
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
                AsyncImage(
                    model = cameraDto.snapshot,
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
                if (cameraDto.hasRecord) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_record),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(dimensionResource(id = R.dimen.small_padding))
                            .size(dimensionResource(id = R.dimen.ic_record_size)),
                        tint = Color.Red
                    )
                }
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
            Text(
                text = cameraDto.name,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.medium_padding))
            )
        }
    }
}