package com.example.aidar_badirov_hw_5_2

interface LoveContract {

    interface View {
        fun showResult(result: LoveModel)
        fun showError(message: String)
    }
    interface Presenter {
        fun getPercentage(firstName: String, secondName: String)
    }
}