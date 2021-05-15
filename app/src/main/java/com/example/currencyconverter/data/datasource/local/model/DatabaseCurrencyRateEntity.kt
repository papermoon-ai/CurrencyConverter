package com.example.currencyconverter.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.currencyconverter.domain.model.Currencies
import com.example.currencyconverter.domain.model.CurrencyType
import com.example.currencyconverter.domain.model.CurrencyRate

@Entity(tableName = "currency_rate")
data class DatabaseCurrencyRate(
    @PrimaryKey
    val currency: CurrencyType,
    val rate: Double
)

fun List<DatabaseCurrencyRate>.asDomainObject(): CurrencyRate {
    return CurrencyRate(Currencies.BASE, associateBy({ it.currency }, { it.rate }).toMap())
}