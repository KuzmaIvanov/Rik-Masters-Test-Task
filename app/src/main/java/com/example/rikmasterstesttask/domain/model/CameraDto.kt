package com.example.rikmasterstesttask.domain.model

data class CameraDto(
    val id: Long,
    val name: String,
    val snapshot: String,
    val roomName: String?,
    val isFavorite: Boolean,
    val hasRecord: Boolean
)