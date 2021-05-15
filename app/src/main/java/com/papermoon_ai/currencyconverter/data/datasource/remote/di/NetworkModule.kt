package com.papermoon_ai.currencyconverter.data.datasource.remote.di

import com.papermoon_ai.currencyconverter.data.datasource.remote.CurrencyRateApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://api.exchangeratesapi.io/v1/"

    @Singleton
    @Provides
    fun provideRetrofitBuilder(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): CurrencyRateApiService {
        return retrofit.create(CurrencyRateApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMoshiFactory(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }
}