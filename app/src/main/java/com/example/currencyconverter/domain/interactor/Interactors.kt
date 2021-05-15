package com.example.currencyconverter.domain.interactor

import com.example.currencyconverter.domain.usecase.GetLocalCurrencyRateUseCase
import com.example.currencyconverter.domain.usecase.GetNetworkCurrencyRateUseCase
import com.example.currencyconverter.domain.usecase.SaveCurrencyRateUseCase
import javax.inject.Inject

data class Interactors @Inject constructor(
    val getNetworkCurrencyRateUseCase: GetNetworkCurrencyRateUseCase,
    val getLocalCurrencyRateUseCase: GetLocalCurrencyRateUseCase,
    val saveCurrencyRateUseCase: SaveCurrencyRateUseCase
)