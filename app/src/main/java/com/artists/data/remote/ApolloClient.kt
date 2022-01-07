package com.artists.data.remote

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by Mohammed MAADELLAOUI on 05/01/2022.
 */
class ApolloClientProvider {

    fun provide(apiUrl: String): ApolloClient {
        val logger = HttpLoggingInterceptor()
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(logger)

        return ApolloClient.builder()
            .serverUrl(apiUrl)
            .okHttpClient(okHttpClient.build())
            .build()
    }

}