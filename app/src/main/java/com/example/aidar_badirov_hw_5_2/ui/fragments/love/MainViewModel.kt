package com.example.aidar_badirov_hw_5_2.ui.fragments.love

import android.health.connect.datatypes.units.Percentage
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aidar_badirov_hw_5_2.data.local.HistoryDao
import com.example.aidar_badirov_hw_5_2.data.local.HistoryEntity
import com.example.aidar_badirov_hw_5_2.data.network.LoveApiService
import com.example.aidar_badirov_hw_5_2.data.network.LoveResult
import com.example.aidar_badirov_hw_5_2.data.repository.LoveRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: LoveRepository) : ViewModel() {

    val error: LiveData<String> = repository.error
    val flag: LiveData<Boolean> = repository.flag
    val historyList: LiveData<List<HistoryEntity>> = repository.getHistoryList()
    fun getLovePercentage(firstName: String, secondName: String): LiveData<LoveResult> {
        return repository.getLovePercentage(firstName, secondName)
    }

    fun deleteHistory(historyEntity: HistoryEntity) {
        repository.deleteHistory(historyEntity)
    }
}