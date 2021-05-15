package com.example.currencyconverter.domain.model

object Currencies {
    val BASE = CurrencyType.EUR
    val SYMBOLS = CurrencyType.values().map { it.toString() }
        .toString().removeSurrounding("[", "]")
}