package com.example.aidar_badirov_hw_5_2.ui.fragments.history

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aidar_badirov_hw_5_2.R
import com.example.aidar_badirov_hw_5_2.data.local.HistoryDao
import com.example.aidar_badirov_hw_5_2.data.local.HistoryEntity
import com.example.aidar_badirov_hw_5_2.databinding.FragmentHistoryBinding
import com.example.aidar_badirov_hw_5_2.di.ApplicationModule
import com.example.aidar_badirov_hw_5_2.ui.fragments.love.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : Fragment(), HistoryAdapter.OnItemLongClickListener {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HistoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        adapter = HistoryAdapter(this)
        val list = ApplicationModule().provideDatabase(requireContext()).getHistoryDao().getAllHistorySorted()
        binding.rvHistory.adapter = adapter
        adapter.submitList(list.value)
        initListener()

        viewModel.historyList.observe(viewLifecycleOwner, Observer { historyList ->
            adapter.submitList(historyList)
        })
    }

    private fun initListener() = with(binding) {
        btnBack.setOnClickListener {
            findNavController().navigate(R.id.loveCalculatorFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        updateList()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun updateList() {
        val historyList =
            ApplicationModule().provideDatabase(requireContext()).getHistoryDao().getAllHistorySorted()
            adapter.submitList(historyList.value)
    }

    override fun onItemLongClicked(historyEntity: HistoryEntity) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Удалить элемент")
            .setMessage("Вы уверены, что хотите удалить этот элемент?")
            .setPositiveButton("Да") { _, _ ->
                ApplicationModule().provideDatabase(requireContext()).getHistoryDao().deleteHistory(historyEntity)
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}