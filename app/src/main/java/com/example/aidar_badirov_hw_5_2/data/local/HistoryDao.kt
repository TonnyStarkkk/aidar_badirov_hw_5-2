package com.example.aidar_badirov_hw_5_2.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert
    fun insertHistory(historyEntity: HistoryEntity)

    @Query("SELECT * FROM history_table ORDER BY firstName ASC")
    fun getAllHistorySorted(): List<HistoryEntity>

    @Delete
    fun deleteHistory(historyEntity: HistoryEntity)
}