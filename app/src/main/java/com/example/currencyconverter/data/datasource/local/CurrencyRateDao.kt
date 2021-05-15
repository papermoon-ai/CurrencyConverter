package com.example.currencyconverter.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencyconverter.data.datasource.local.model.DatabaseCurrencyRate

@Dao
interface CurrencyRateDao {
    @Query("Select * from currency_rate")
    fun getCurrencyRate(): List<DatabaseCurrencyRate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCurrencyRate(currencyRate: List<DatabaseCurrencyRate>)
}