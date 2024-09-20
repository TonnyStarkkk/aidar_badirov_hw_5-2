package com.example.aidar_badirov_hw_5_2.ui.fragments.onboard


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.aidar_badirov_hw_5_2.Application.SharedPreferences
import com.example.aidar_badirov_hw_5_2.R

import com.example.aidar_badirov_hw_5_2.databinding.FragmentOnBoardBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class OnBoardFragment @Inject constructor(private  val sharedPreferences: SharedPreferences )
    : Fragment() {

    private var _binding: FragmentOnBoardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
    }

    private fun initialize() {
        binding.viewpager2.adapter = OnBoardAdapter(this)
    }

    private fun setupListener() = with(binding.viewpager2) {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) = with(binding.btnStart) {
                super.onPageSelected(position)
                if (position == 3) {
                    visibility = View.VISIBLE
                } else {
                    visibility = View.GONE
                }
            }
        })
        binding.btnStart.setOnClickListener {
            if (currentItem < 4) {
                currentItem += 3
            }
        }
        binding.btnStart.setOnClickListener {
            sharedPreferences.setOnboardingComplete(true)
            findNavController().navigate(R.id.action_onBoardFragment_to_loveCalculatorFragment)
        }
    }
}