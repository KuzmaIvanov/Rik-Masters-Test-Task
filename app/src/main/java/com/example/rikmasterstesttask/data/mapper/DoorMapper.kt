package com.example.rikmasterstesttask.data.mapper

import com.example.rikmasterstesttask.data.database.entity.DoorEntity
import com.example.rikmasterstesttask.data.network.entity.Door
import com.example.rikmasterstesttask.domain.model.DoorDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DoorMapper @Inject constructor() {
    fun mapToDto(door: Door): DoorDto = with(door) {
        DoorDto(
            id = id,
            name = name,
            room = room,
            isFavorite = isFavorite,
            snapshot = snapshot
        )
    }

    fun mapToDto(doorEntity: DoorEntity): DoorDto = with(doorEntity) {
        DoorDto(
            id = id,
            name = name,
            room = room,
            isFavorite = isFavorite,
            snapshot = snapshot
        )
    }

    fun mapToEntity(doorDto: DoorDto): DoorEntity = with(doorDto) {
        DoorEntity(
            id = id,
            name = name,
            room = room,
            isFavorite = isFavorite,
            snapshot = snapshot
        )
    }
}