package com.example.currencyconverter.data.datasource.di

import com.example.currencyconverter.domain.repository.CurrencyRateRepository
import com.example.currencyconverter.data.CurrencyRateRepositoryImpl
import com.example.currencyconverter.data.datasource.local.CurrencyRateDao
import com.example.currencyconverter.data.datasource.remote.CurrencyRateApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(localSource: CurrencyRateDao, remoteSource: CurrencyRateApiService)
            : CurrencyRateRepository {
        return CurrencyRateRepositoryImpl(localSource, remoteSource)
    }
}