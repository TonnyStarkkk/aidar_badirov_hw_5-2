package com.example.aidar_badirov_hw_5_2.data.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoveResult(
    @SerializedName("fname") val firstName: String,
    @SerializedName("sname") val secondName: String,
    @SerializedName("percentage") val percentage: String,
    @SerializedName("result") val result: String
): Serializable