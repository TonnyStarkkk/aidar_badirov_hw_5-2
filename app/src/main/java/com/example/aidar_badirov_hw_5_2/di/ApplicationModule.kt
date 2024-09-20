package com.example.aidar_badirov_hw_5_2.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.aidar_badirov_hw_5_2.data.local.HistoryDao
import com.example.aidar_badirov_hw_5_2.data.local.HistoryDataBase
import com.example.aidar_badirov_hw_5_2.data.network.LoveApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : HistoryDataBase {
        return Room.databaseBuilder(
            context,
            HistoryDataBase::class.java,
            "HistoryDataBase"
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideHistoryDao(database: HistoryDataBase) : HistoryDao {
        return database.getHistoryDao()
    }

    @Provides
    @Singleton
    fun provideLoveApiService() : LoveApiService {
        return Retrofit.Builder()
           .baseUrl("https://love-calculator.p.rapidapi.com/")
           .addConverterFactory(GsonConverterFactory.create())
           .build().create(LoveApiService::class.java)
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context) : SharedPreferences {
        return context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }
}