package com.example.aidar_badirov_hw_5_2.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo("first_name")
    val firstName: String,
    @ColumnInfo("second_name")
    val secondName: String,
    @ColumnInfo("love_percentage")
    val lovePercentage: String
)