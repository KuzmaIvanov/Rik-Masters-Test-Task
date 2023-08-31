package com.example.rikmasterstesttask.domain.model

data class DoorDto(
    val id: Long,
    val name: String,
    val room: String?,
    val isFavorite: Boolean,
    val snapshot: String?
)
