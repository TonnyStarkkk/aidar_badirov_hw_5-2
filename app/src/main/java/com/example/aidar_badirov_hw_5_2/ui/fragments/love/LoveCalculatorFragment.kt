package com.example.aidar_badirov_hw_5_2.ui.fragments.love

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.aidar_badirov_hw_5_2.data.network.LoveResult
import com.example.aidar_badirov_hw_5_2.R
import com.example.aidar_badirov_hw_5_2.databinding.FragmentLoveCalculatorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoveCalculatorFragment : Fragment() {

    private var _binding: FragmentLoveCalculatorBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoveCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        setupObservers()
    }

    private fun initListener() = with(binding) {
        btnCalc.setOnClickListener {
            val firstName = etFname.text.toString().trim()
            val secondName = etSname.text.toString().trim()

            if (firstName.isBlank() || secondName.isBlank()) {
                Toast.makeText(requireContext(), "Enter both name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.getPercentage(firstName, secondName)
        }
    }

    private fun setupObservers() {
        viewModel.loveResultData.observe(viewLifecycleOwner) { result ->
            navigateToResultFragment(result)
        }
        viewModel.errorData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToResultFragment(loveResult: LoveResult) {
        val bundle = Bundle().apply {
            putString("percentage", loveResult.percentage)
            putString("result", loveResult.result)
        }
        findNavController().navigate(R.id.action_loveCalculatorFragment_to_resultFragment, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


