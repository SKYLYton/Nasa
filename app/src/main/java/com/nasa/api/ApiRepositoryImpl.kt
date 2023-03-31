package com.nasa.api

import com.nasa.BuildConfig
import com.nasa.api.response.ImageResponse
import com.nasa.extension.getTimeToString
import java.util.*
import javax.inject.Inject

/**
 * @author Fedotov Yakov
 */
class ApiRepositoryImpl @Inject constructor(
    private val retrofitBuilder: RetrofitBuilder,
    private var api: Api
) : ApiRepository {

    override var config: RepositoryConfig = RepositoryConfig(BuildConfig.NASA_URL)
        set(value) {
            if (field == value) {
                return
            }
            field = value
            val retrofit = retrofitBuilder.build(value)
            api = retrofit.create(Api::class.java)
        }

    override suspend fun getImage(date: Date): ImageResponse = api.getImage(date.getTimeToString())

    override suspend fun getImages(count: Int): List<ImageResponse> = if (count <= 0) {
        api.getImages()
    } else {
        api.getImages(count)
    }
}

