package com.example.aidar_badirov_hw_5_2.ui.fragments.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aidar_badirov_hw_5_2.R
import com.example.aidar_badirov_hw_5_2.data.local.HistoryDao
import com.example.aidar_badirov_hw_5_2.data.local.HistoryEntity
import com.example.aidar_badirov_hw_5_2.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment @Inject constructor(private val dao: HistoryDao) : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var historyAdapter: HistoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historyAdapter = HistoryAdapter { historyEntity ->
            showDeleteDialog(historyEntity)
        }

        binding.rvHistory.apply {
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(context)
        }

        lifecycleScope.launch {
            val historyList = dao.getAllHistorySorted()
            historyAdapter.submitList(historyList)
        }

        binding.backResultImg.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun showDeleteDialog(historyEntity: HistoryEntity) {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.delete_dialog, null)

        dialogView.findViewById<TextView>(R.id.dialog_title).text = "Delete Item"
        dialogView.findViewById<TextView>(R.id.dialog_message).text = "Are you sure, you want to delete this item?"

        val builder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setPositiveButton("Yes") { dialog, _ ->
                lifecycleScope.launch {
                    dao.deleteHistory(historyEntity)
                    val updatedList = dao.getAllHistorySorted()
                    historyAdapter.submitList(updatedList)
                }
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        builder.show()
    }
}