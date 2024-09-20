package com.example.aidar_badirov_hw_5_2.ui.fragments.love

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.aidar_badirov_hw_5_2.R
import com.example.aidar_badirov_hw_5_2.data.network.LoveResult
import com.example.aidar_badirov_hw_5_2.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillData()
        initListener()
    }

    private fun initListener() = with(binding) {

        btnTryAgain.setOnClickListener {
            findNavController().navigate(R.id.loveCalculatorFragment)
        }

        historyImg.setOnClickListener {
            findNavController().navigate(R.id.historyFragment)
        }
    }

    private fun fillData() = with(binding) {
        setFragmentResultListener("key") { _, bundle ->
            val result = bundle.getSerializable("data") as? LoveResult
            tvMeResult.text = result?.firstName
            tvYouResult.text = result?.secondName
            tvProcent.text = result?.percentage
            tvResult.text = result?.result
        }

    }
}