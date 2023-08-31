package com.example.rikmasterstesttask.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rikmasterstesttask.data.database.dao.CameraDao
import com.example.rikmasterstesttask.data.database.dao.DoorDao
import com.example.rikmasterstesttask.data.database.entity.CameraEntity
import com.example.rikmasterstesttask.data.database.entity.DoorEntity

@Database(
    entities = [
        CameraEntity::class,
        DoorEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCameraDao(): CameraDao

    abstract fun getDoorDao(): DoorDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val Lock = Any()
        private const val DATABASE_NAME = "test_task_database"

        operator fun invoke(context: Context) = instance ?: synchronized(Lock) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME,
        ).build()
    }
}