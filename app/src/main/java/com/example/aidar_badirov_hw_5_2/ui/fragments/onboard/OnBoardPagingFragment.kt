package com.example.aidar_badirov_hw_5_2.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aidar_badirov_hw_5_2.R
import com.example.aidar_badirov_hw_5_2.databinding.FragmentOnBoardPagingBinding


class OnBoardPagingFragment : Fragment() {

    private var _binding: FragmentOnBoardPagingBinding? = null
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
    }

    private fun initialize() {
        val position = arguments?.getInt(ARG_ONBOARD_POSITION) ?: 0
        with(binding) {
            when(position) {
                0 -> {
                    tvTitle.text = "It`s Funs and many more"
                    lottie.setAnimation(R.raw.lottie1)
                }
                1 -> {
                    tvTitle.text = "Have a good time " +
                            "You should take a time to help those who need you"
                    lottie.setAnimation(R.raw.lottie2)
                }
                2 -> {
                    tvTitle.text = "Cherishing love" +
                            "It`s now no longer possible for you to cherish love"
                    lottie.setAnimation(R.raw.lottie3)
                }
                3 -> {
                    tvTitle.text = "Have a breakup? " +
                            "We have made the correction  for you don`t worry Maybe someone is waiting for you"
                    lottie.setAnimation(R.raw.lottie1)
                }
            }
        }
    }

    companion object {
        const val ARG_ONBOARD_POSITION = "onBoard"
    }
}