package com.nasa.model

import com.nasa.api.response.ImageResponse

data class ImageModel(
    val date: String = "",
    val copyright: String = "",
    val mediaType: String = "",
    val hdurl: String = "",
    val serviceVersion: String = "",
    val explanation: String = "",
    val title: String = "",
    val url: String = "",
) {
    val isEmpty: Boolean
        get() = date.isEmpty() && copyright.isEmpty() && mediaType.isEmpty()
                && hdurl.isEmpty() && serviceVersion.isEmpty() && explanation.isEmpty()
                && title.isEmpty() && url.isEmpty()

}

val ImageResponse.toModel: ImageModel
    get() = ImageModel(
        date ?: "",
        copyright ?: "",
        mediaType ?: "",
        hdurl ?: "",
        serviceVersion ?: "",
        explanation ?: "",
        title ?: "",
        url ?: ""
    )
