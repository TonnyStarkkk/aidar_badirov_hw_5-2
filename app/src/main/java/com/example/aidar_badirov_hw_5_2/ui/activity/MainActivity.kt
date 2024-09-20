package com.example.aidar_badirov_hw_5_2.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.aidar_badirov_hw_5_2.Application.SharedPreferences
import com.example.aidar_badirov_hw_5_2.R
import com.example.aidar_badirov_hw_5_2.databinding.ActivityMainBinding
import com.example.aidar_badirov_hw_5_2.ui.fragments.love.LoveCalculatorFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        if (!sharedPreferences.isOnboardingComplete()) {
            sharedPreferences.setOnboardingComplete(true)
        } else {
            navController.navigate(R.id.loveCalculatorFragment)
        }
    }
}