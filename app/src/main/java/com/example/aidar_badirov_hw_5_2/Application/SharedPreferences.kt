package com.example.aidar_badirov_hw_5_2.Application

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferences @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val SHOWN = "shown"
    }

    fun isOnboardingComplete(): Boolean {
        return sharedPreferences.getBoolean(SHOWN, false)
    }

    fun setOnboardingComplete(b: Boolean) {
        sharedPreferences.edit().putBoolean(SHOWN, true).apply()
    }
}