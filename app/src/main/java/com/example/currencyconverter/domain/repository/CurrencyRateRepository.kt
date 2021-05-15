package com.example.currencyconverter.domain.repository

import com.example.currencyconverter.domain.model.CurrencyRate
import com.example.currencyconverter.domain.result.Resource

interface CurrencyRateRepository {
    suspend fun getRateFromLocalSource(): Resource<CurrencyRate>
    suspend fun getRateFromRemoteSource(): Resource<CurrencyRate>
    suspend fun saveRateToLocalSource(rate: CurrencyRate): Resource<Unit>
}