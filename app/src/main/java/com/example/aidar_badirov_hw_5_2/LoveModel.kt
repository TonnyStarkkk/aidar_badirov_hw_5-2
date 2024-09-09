package com.example.aidar_badirov_hw_5_2

import com.google.gson.annotations.SerializedName

data class LoveModel(
    @SerializedName("fname")
    val firstName: String,
    @SerializedName("sname")
    val secondName: String,
    @SerializedName("percentage")
    val percentage: String,
    @SerializedName("result")
    val result: String
)