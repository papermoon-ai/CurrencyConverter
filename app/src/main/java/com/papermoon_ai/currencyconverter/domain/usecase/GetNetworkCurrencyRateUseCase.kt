package com.papermoon_ai.currencyconverter.domain.usecase

import com.papermoon_ai.currencyconverter.data.CurrencyRateRepositoryImpl
import com.papermoon_ai.currencyconverter.domain.model.CurrencyRate
import com.papermoon_ai.currencyconverter.domain.result.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetNetworkCurrencyRateUseCase @Inject constructor(
    private val repository: CurrencyRateRepositoryImpl
) : UseCase<CurrencyRate, Unit> {
    override suspend fun execute(params: Unit): Resource<CurrencyRate> {
        return withContext(Dispatchers.IO) {
            repository.getRateFromRemoteSource()
        }
    }
}