package com.example.rikmasterstesttask.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rikmasterstesttask.data.database.entity.DoorEntity

@Dao
interface DoorDao {
    @Query("SELECT * FROM door")
    suspend fun getDoors(): List<DoorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(doors: List<DoorEntity>)

    @Query("UPDATE door SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun updateIsFavorite(id: Long, isFavorite: Boolean)

    @Query("UPDATE door SET name = :name WHERE id = :id")
    suspend fun updateName(id: Long, name: String)
}