package com.example.currencyconverter.data

import com.example.currencyconverter.BuildConfig
import com.example.currencyconverter.data.datasource.local.CurrencyRateDao
import com.example.currencyconverter.data.datasource.local.exception.NoEntriesException
import com.example.currencyconverter.data.datasource.local.model.asDomainObject
import com.example.currencyconverter.data.datasource.remote.CurrencyRateApiService
import com.example.currencyconverter.data.datasource.remote.model.asDomainObject
import com.example.currencyconverter.domain.model.Currencies
import com.example.currencyconverter.domain.model.CurrencyRate
import com.example.currencyconverter.domain.model.asDatabaseObject
import com.example.currencyconverter.domain.repository.CurrencyRateRepository
import com.example.currencyconverter.domain.result.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRateRepositoryImpl @Inject constructor
    (
    private val localDataSource: CurrencyRateDao,
    private val remoteDataSource: CurrencyRateApiService
) : CurrencyRateRepository {

    override suspend fun getRateFromLocalSource(): Resource<CurrencyRate> {
        return try {
            val currencyRate = localDataSource.getCurrencyRate().asDomainObject()

            if (currencyRate.rate.isNotEmpty()) Resource.Success(currencyRate) else
                Resource.Failure(NoEntriesException())

        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getRateFromRemoteSource(): Resource<CurrencyRate> {
        return try {
            val currencyRate = remoteDataSource
                .getCurrencyRate(
                    BuildConfig.ACCESS_KEY,
                    Currencies.BASE.toString(),
                    Currencies.SYMBOLS
                )
                .await().asDomainObject()

            Resource.Success(currencyRate)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun saveRateToLocalSource(rate: CurrencyRate): Resource<Unit> {
        return try {
            localDataSource.saveCurrencyRate(rate.asDatabaseObject())
            Resource.Success(Unit)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}