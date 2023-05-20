package com.papermoon.currencyconverter.domain.repository

import com.papermoon.currencyconverter.domain.model.CurrencyRate
import com.papermoon.currencyconverter.domain.result.Resource

interface CurrencyRateRepository {
    suspend fun getRateFromLocalSource(): Resource<CurrencyRate>
    suspend fun getRateFromRemoteSource(): Resource<CurrencyRate>
    suspend fun saveRateToLocalSource(rate: CurrencyRate): Resource<Unit>
}
