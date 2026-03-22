package com.example.smarthomejetpack.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.smarthomejetpack.database.dao.SensorDao
import com.example.smarthomejetpack.database.entities.SensorData

@Database(
    entities = [SensorData::class],
    version = 1,
    exportSchema = false,
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun sensorDao(): SensorDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "smart_home_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}