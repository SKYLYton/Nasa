package com.nasa.api

import com.nasa.api.RepositoryConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/**
 * @author Fedotov Yakov
 */
class RetrofitBuilder @Inject constructor(private val okHttpClient: OkHttpClient) {

    fun build(repositoryConfig: RepositoryConfig): Retrofit {
        return Retrofit.Builder()
            .baseUrl(repositoryConfig.url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}