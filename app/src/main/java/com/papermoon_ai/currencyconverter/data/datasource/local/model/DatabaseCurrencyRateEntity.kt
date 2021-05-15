package com.papermoon_ai.currencyconverter.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.papermoon_ai.currencyconverter.domain.model.Currencies
import com.papermoon_ai.currencyconverter.domain.model.CurrencyType
import com.papermoon_ai.currencyconverter.domain.model.CurrencyRate

@Entity(tableName = "currency_rate")
data class DatabaseCurrencyRate(
    @PrimaryKey
    val currency: CurrencyType,
    val rate: Double
)

fun List<DatabaseCurrencyRate>.asDomainObject(): CurrencyRate {
    return CurrencyRate(Currencies.BASE, associateBy({ it.currency }, { it.rate }).toMap())
}