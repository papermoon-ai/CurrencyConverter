package com.example.currencyconverter.features.callback

import com.example.currencyconverter.domain.model.CurrencyType

interface OnChangeCurrencyListener {
    fun changeCurrency(currency: CurrencyType)
}