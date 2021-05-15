package com.papermoon_ai.currencyconverter.features.callback

import com.papermoon_ai.currencyconverter.domain.model.CurrencyType

interface OnChangeCurrencyListener {
    fun changeCurrency(currency: CurrencyType)
}