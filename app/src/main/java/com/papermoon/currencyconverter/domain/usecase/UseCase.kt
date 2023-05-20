package com.papermoon.currencyconverter.domain.usecase

import com.papermoon.currencyconverter.domain.result.Resource

interface UseCase<T, P> {
    suspend fun execute(params: P): Resource<T>
}
