package com.example.aidar_badirov_hw_5_2.data.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aidar_badirov_hw_5_2.data.local.HistoryDao
import com.example.aidar_badirov_hw_5_2.data.local.HistoryEntity
import com.example.aidar_badirov_hw_5_2.data.network.LoveApiService
import com.example.aidar_badirov_hw_5_2.data.network.LoveResult
import com.example.aidar_badirov_hw_5_2.extension.toEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoveRepository @Inject constructor(
    private val api: LoveApiService,
    private val dao: HistoryDao
) {

    private var lovePercentageLv = MutableLiveData<LoveResult>()
    var error = MutableLiveData<String>()
    var flag = MutableLiveData<Boolean>()

    fun getLovePercentage(firstName: String, secondName: String): MutableLiveData<LoveResult> {
        api.getPercentage(
            firstName = firstName,
            secondName = secondName,
            key = "ef2a376a4fmsh5d299d7f68ef44bp156b9cjsn917023ff7c0a",
            host = "love-calculator.p.rapidapi.com"
        ).enqueue(object : Callback<LoveResult> {
            override fun onResponse(call: Call<LoveResult>, response: Response<LoveResult>) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        val historyEntity = it.toEntity()
                        dao.insertHistory(historyEntity)
                    }
                    lovePercentageLv.postValue(response.body())
                    flag.postValue(true)
                }
            }

            override fun onFailure(call: Call<LoveResult>, t: Throwable) {
                error.postValue(t.message)
                flag.postValue(false)
            }
        })
        return lovePercentageLv
    }

    fun deleteHistory(historyEntity: HistoryEntity) {
        dao.deleteHistory(historyEntity)
    }

    fun getHistoryList(): LiveData<List<HistoryEntity>> {
        return dao.getAllHistorySorted()
    }
}