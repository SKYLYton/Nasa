package com.nasa.api.helper

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

/**
 * Перехватчик запросов для подстановки данных сессии и данных устройства
 *
 * @author Fedotov Yakov
 */
class InterceptorApi @Inject constructor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()

        val response = chain.proceed(request.build())

        return response
    }

    private fun processResponse(response: Response): Response {
        val body = response.body?.string()
        return updateResponse(response, wrapBodyIfArray(body))
    }

    private fun wrapBodyIfArray(body: String?): String {
        return body?.run {
            if (startsWith("[")) {
                wrappedArray
            } else {
                this
            }
        } ?: ""
    }

    private fun updateResponse(response: Response, body: String): Response {
        return response.newBuilder().body(body.toResponseBody(response.body?.contentType())).build()
    }

    private inline val String.wrappedArray: String
        get() = "{\"items\":$this}"
}

private const val TOKEN = "Authorization"
private const val VERSION = "Version"
private const val PLATFORM = "Platform"