package com.example.aidar_badirov_hw_5_2.ui.fragments.love

import android.health.connect.datatypes.units.Percentage
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aidar_badirov_hw_5_2.data.local.HistoryDao
import com.example.aidar_badirov_hw_5_2.data.local.HistoryEntity
import com.example.aidar_badirov_hw_5_2.data.network.LoveApiService
import com.example.aidar_badirov_hw_5_2.data.network.LoveResult
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: LoveApiService,
    private val dao: HistoryDao
) : ViewModel() {

    val loveResultData = MutableLiveData<LoveResult>()
    val errorData = MutableLiveData<String>()

    fun getPercentage(firstName: String, secondName: String) {
        api.getPercentage(
            firstName = firstName,
            secondName = secondName,
            key = "ef2a376a4fmsh5d299d7f68ef44bp156b9cjsn917023ff7c0a",
            host = "love-calculator.p.rapidapi.com"
        ).enqueue(object : Callback<LoveResult> {
            override fun onResponse(call: Call<LoveResult>, response: Response<LoveResult>) {
                if (response.isSuccessful && response.body() != null) {
                    val loveResult = response.body()!!
                    saveToHistory(firstName, secondName, loveResult)
                    loveResultData.postValue(loveResult)
                } else {
                    errorData.postValue("Couldn`t get a correct answer")
                }
            }

            override fun onFailure(call: Call<LoveResult>, t: Throwable) {
                errorData.postValue("Connection Error")
            }
        })
    }

    private fun saveToHistory(firstName: String, secondName: String, loveResult: LoveResult) {
        val historyEntity = HistoryEntity(
            firstName = firstName,
            secondName = secondName,
            result = loveResult.result,
            lovePercentage = loveResult.percentage,
        )
        dao.insertHistory(historyEntity)
    }
}