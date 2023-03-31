package com.nasa.api

import com.nasa.api.response.ImageResponse
import com.nasa.extension.getTimeToString
import retrofit2.http.*
import java.util.Date

/**
 * Api для Retrofit
 *
 * @author Fedotov Yakov
 */
interface Api {

    /**
     * Тестовый запрос
     */
    @GET("planetary/apod/")
    suspend fun getImage(@Query("date") date: String = Date().getTimeToString()): ImageResponse

    /**
     * Тестовый запрос
     */
    @GET("planetary/apod/")
    suspend fun getImages(@Query("count") count: Int = 10): List<ImageResponse>
}