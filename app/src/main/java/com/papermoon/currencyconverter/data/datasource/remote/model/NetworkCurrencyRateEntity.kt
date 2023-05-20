package com.papermoon.currencyconverter.data.datasource.remote.model

import android.os.Parcelable
import com.papermoon.currencyconverter.domain.model.CurrencyRate
import com.papermoon.currencyconverter.domain.model.CurrencyType
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkCurrencyRate(
    val base: CurrencyType,
    val rates: Map<CurrencyType, Double>
) : Parcelable

fun NetworkCurrencyRate.asDomainObject(): CurrencyRate {
    return CurrencyRate(base, rates)
}
