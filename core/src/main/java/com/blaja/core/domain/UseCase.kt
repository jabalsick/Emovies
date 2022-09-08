package com.blaja.core.domain

interface UseCase {
    interface ResourceUseCase<T : Any> : UseCase {
        suspend fun executeAsync(param: Map<String, Any>? = null, queryType: String? = null): Resource<T>
    }
}
