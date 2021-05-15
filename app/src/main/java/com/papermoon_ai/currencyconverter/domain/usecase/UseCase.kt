package com.papermoon_ai.currencyconverter.domain.usecase

import com.papermoon_ai.currencyconverter.domain.result.Resource

interface UseCase<T, P> {
    suspend fun execute(params: P): Resource<T>
}