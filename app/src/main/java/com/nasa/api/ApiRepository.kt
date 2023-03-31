package com.nasa.api

import android.provider.ContactsContract.RawContacts.Data
import com.nasa.api.response.ImageResponse
import java.util.*

/**
 * @author Fedotov Yakov
 */
interface ApiRepository {
    var config: RepositoryConfig

    /**
     * Тестовый запрос
     */
   suspend fun getImage(date: Date = Date()): ImageResponse


    /**
     * Тестовый запрос
     */
    suspend fun getImages(count: Int = 10): List<ImageResponse>
}