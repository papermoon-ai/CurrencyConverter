package com.papermoon.currencyconverter.domain.usecase

import com.papermoon.currencyconverter.domain.model.CurrencyRate
import com.papermoon.currencyconverter.domain.repository.CurrencyRateRepository
import com.papermoon.currencyconverter.domain.result.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLocalCurrencyRateUseCase @Inject constructor(
    private val repository: CurrencyRateRepository
) : UseCase<CurrencyRate, Unit> {

    override suspend fun execute(params: Unit): Resource<CurrencyRate> {
        return withContext(Dispatchers.IO) {
            repository.getRateFromLocalSource()
        }
    }
}
