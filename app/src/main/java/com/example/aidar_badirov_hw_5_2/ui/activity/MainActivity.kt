package com.example.aidar_badirov_hw_5_2.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aidar_badirov_hw_5_2.R
import com.example.aidar_badirov_hw_5_2.databinding.ActivityMainBinding
import com.example.aidar_badirov_hw_5_2.ui.fragments.LoveCalculatorFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState != null) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoveCalculatorFragment())
            .commit()
        }
    }
}