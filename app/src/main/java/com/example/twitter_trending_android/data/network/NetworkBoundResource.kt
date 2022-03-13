package com.example.twitter_trending_android.data.network

import com.example.twitter_trending_android.data.Resource
import kotlinx.coroutines.flow.*

inline fun <RequestType, ResultType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
    crossinline saveFetchResult: suspend (RequestType) -> Unit
) = flow {
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { (Resource.Error(throwable, it)) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}
