package com.example.aidar_badirov_hw_5_2.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val firstName: String,
    val secondName: String,
    val lovePercentage: String,
    val result: String,
)