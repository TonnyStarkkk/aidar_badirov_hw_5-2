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
object ApplicationModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : HistoryDataBase {
        return Room.databaseBuilder(
            context,
            HistoryDataBase::class.java,
            "app_database"
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideHistoryDao(database: HistoryDataBase) : HistoryDao {
        return database.historyDao()
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
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context) : SharedPreferences {
        return context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesHelper(sharedPreferences: SharedPreferences) : com.example.aidar_badirov_hw_5_2.Application.SharedPreferences {
        return com.example.aidar_badirov_hw_5_2.Application.SharedPreferences(sharedPreferences)
    }
}