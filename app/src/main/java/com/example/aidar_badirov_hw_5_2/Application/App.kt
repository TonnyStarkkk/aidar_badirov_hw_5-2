package com.example.aidar_badirov_hw_5_2.Application

import android.app.Application
import androidx.room.Room
import com.example.aidar_badirov_hw_5_2.data.local.HistoryDao
import com.example.aidar_badirov_hw_5_2.data.local.HistoryDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    var dao: HistoryDao? = null

    override fun onCreate() {
        super.onCreate()

        val room = Room.databaseBuilder(
            this,
            HistoryDataBase::class.java,
            "history_table"
        ).build()

        dao = room.getHistoryDao()
    }
}