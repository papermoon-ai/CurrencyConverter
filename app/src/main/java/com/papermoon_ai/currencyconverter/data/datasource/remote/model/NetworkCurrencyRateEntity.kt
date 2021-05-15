package com.papermoon_ai.currencyconverter.data.datasource.remote.model

import android.os.Parcelable
import com.papermoon_ai.currencyconverter.domain.model.CurrencyType
import com.papermoon_ai.currencyconverter.domain.model.CurrencyRate
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NetworkCurrencyRate(
    val base: CurrencyType,
    val rates: Map<CurrencyType, Double>
) : Parcelable

fun NetworkCurrencyRate.asDomainObject(): CurrencyRate {
    return CurrencyRate(base, rates)
}