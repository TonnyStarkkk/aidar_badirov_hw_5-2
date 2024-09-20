package com.example.aidar_badirov_hw_5_2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1)
abstract class HistoryDataBase : RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao
}