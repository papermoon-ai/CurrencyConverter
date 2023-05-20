package com.papermoon.currencyconverter.data.datasource.remote

import com.papermoon.currencyconverter.data.datasource.remote.model.NetworkCurrencyRate
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


interface CurrencyRateApiService {
    @GET("latest")
    fun getCurrencyRate(
        @Query("access_key") key: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): Deferred<NetworkCurrencyRate>
}
