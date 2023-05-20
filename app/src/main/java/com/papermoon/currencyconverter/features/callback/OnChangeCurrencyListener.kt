package com.papermoon.currencyconverter.features.callback

import com.papermoon.currencyconverter.domain.model.CurrencyType

interface OnChangeCurrencyListener {
    fun changeCurrency(currency: CurrencyType)
}
