package com.example.aidar_badirov_hw_5_2.ui.fragments.love

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
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
    }

    private fun initListener() = with(binding) {
        btnHistory.setOnClickListener {
            findNavController().navigate(R.id.historyFragment)
        }
        btnCalc.setOnClickListener {
            viewModel.getLovePercentage(
                firstName = etFname.text.toString().trim(),
                secondName = etSname.text.toString().trim()
            ).observe(viewLifecycleOwner) { loveResult ->
                setFragmentResult(
                    "key", bundleOf("data" to loveResult)
                )

                viewModel.flag.observe(viewLifecycleOwner) { flag ->
                    Log.e("TAG", "initListener: $flag")
                    if (flag == false) {
                        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        findNavController().navigate(R.id.resultFragment)
                    }
                }
            }
        }
    }
}


