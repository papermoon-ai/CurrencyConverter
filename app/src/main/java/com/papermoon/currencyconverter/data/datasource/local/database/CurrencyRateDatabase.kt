package com.papermoon.currencyconverter.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.papermoon.currencyconverter.data.datasource.local.CurrencyRateDao
import com.papermoon.currencyconverter.data.datasource.local.model.DatabaseCurrencyRate

@Database(entities = [DatabaseCurrencyRate::class], version = 1)
abstract class CurrencyRateDatabase : RoomDatabase() {
    abstract fun currencyRateDao(): CurrencyRateDao
}
