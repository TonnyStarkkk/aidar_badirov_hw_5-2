package com.example.aidar_badirov_hw_5_2.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.aidar_badirov_hw_5_2.ui.fragments.onboard.OnBoardPagingFragment.Companion.ARG_ONBOARD_POSITION

class OnBoardAdapter(fragment: OnBoardFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = OnBoardPagingFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_ONBOARD_POSITION, position)
        }
        return fragment
    }
}