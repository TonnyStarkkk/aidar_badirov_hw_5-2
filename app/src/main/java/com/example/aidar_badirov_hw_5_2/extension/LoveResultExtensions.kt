package com.example.aidar_badirov_hw_5_2.extension

import com.example.aidar_badirov_hw_5_2.data.local.HistoryEntity
import com.example.aidar_badirov_hw_5_2.data.network.LoveResult

fun LoveResult.toEntity() : HistoryEntity {
    return HistoryEntity(
        firstName = this.firstName,
        secondName = this.secondName,
        lovePercentage = this.percentage
    )
}