package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.domain.result.Resource

interface UseCase<T, P> {
    suspend fun execute(params: P): Resource<T>
}