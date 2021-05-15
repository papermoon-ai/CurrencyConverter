package com.papermoon_ai.currencyconverter.domain.model

object Currencies {
    val BASE = CurrencyType.EUR
    val SYMBOLS = CurrencyType.values().map { it.toString() }
        .toString().removeSurrounding("[", "]")
}