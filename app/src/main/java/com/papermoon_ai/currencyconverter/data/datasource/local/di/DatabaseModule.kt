package com.papermoon_ai.currencyconverter.data.datasource.local.di

import android.content.Context
import androidx.room.Room
import com.papermoon_ai.currencyconverter.data.datasource.local.CurrencyRateDao
import com.papermoon_ai.currencyconverter.data.datasource.local.database.CurrencyRateDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDao(database: CurrencyRateDatabase): CurrencyRateDao {
        return database.currencyRateDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CurrencyRateDatabase {
        return Room.databaseBuilder(
            context, CurrencyRateDatabase::class.java,
            "Currency_rate_database"
        ).build()
    }
}