package com.papermoon_ai.currencyconverter.domain.repository

import com.papermoon_ai.currencyconverter.domain.model.CurrencyRate
import com.papermoon_ai.currencyconverter.domain.result.Resource

interface CurrencyRateRepository {
    suspend fun getRateFromLocalSource(): Resource<CurrencyRate>
    suspend fun getRateFromRemoteSource(): Resource<CurrencyRate>
    suspend fun saveRateToLocalSource(rate: CurrencyRate): Resource<Unit>
}