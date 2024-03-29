package com.papermoon.currencyconverter.domain.usecase

import com.papermoon.currencyconverter.domain.model.CurrencyRate
import com.papermoon.currencyconverter.domain.repository.CurrencyRateRepository
import com.papermoon.currencyconverter.domain.result.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveCurrencyRateUseCase @Inject constructor(private val repository: CurrencyRateRepository) :
    UseCase<Unit, CurrencyRate> {

    override suspend fun execute(params: CurrencyRate): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            repository.saveRateToLocalSource(params)
        }
    }
}
