package com.papermoon_ai.currencyconverter.domain.model

import com.papermoon_ai.currencyconverter.data.datasource.local.model.DatabaseCurrencyRate

data class CurrencyRate(var base: CurrencyType, var rate: Map<CurrencyType, Double>)

fun CurrencyRate.getRate(base: CurrencyType, target: CurrencyType): Double? {
    if (this.base == base) {
        return rate[target]
    }

    val rate = 1 / rate[base]!!
    return rate * this.rate[target]!!
}

fun CurrencyRate.asDatabaseObject(): List<DatabaseCurrencyRate> {
    val databaseObject = ArrayList<DatabaseCurrencyRate>()

    rate.forEach {
        databaseObject.add(DatabaseCurrencyRate(it.key, it.value))
    }

    return databaseObject
}