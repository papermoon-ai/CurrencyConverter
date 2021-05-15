package com.papermoon_ai.currencyconverter.domain.interactor

import com.papermoon_ai.currencyconverter.domain.usecase.GetLocalCurrencyRateUseCase
import com.papermoon_ai.currencyconverter.domain.usecase.GetNetworkCurrencyRateUseCase
import com.papermoon_ai.currencyconverter.domain.usecase.SaveCurrencyRateUseCase
import javax.inject.Inject

data class Interactors @Inject constructor(
    val getNetworkCurrencyRateUseCase: GetNetworkCurrencyRateUseCase,
    val getLocalCurrencyRateUseCase: GetLocalCurrencyRateUseCase,
    val saveCurrencyRateUseCase: SaveCurrencyRateUseCase
)