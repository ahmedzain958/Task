package com.zainco.task.base.data.remote

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.zainco.task.base.data.exception.APIException
import com.zainco.task.base.data.exception.NetworkException
import com.zainco.task.base.data.resources.repository.ResourcesRepository
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import java.net.SocketTimeoutException
import java.nio.charset.Charset

class ErrorMappingInterceptor(
    private val gsonParser: Gson,
    private val resourcesRepository: ResourcesRepository
) : Interceptor {
    private val keyJson = "json"
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response: Response
        try {
            response = chain.proceed(request)
        } catch (e: IOException) {
            if (e is SocketTimeoutException) {
                throw NetworkException(resourcesRepository.getSocketTimeoutExceptionMessage())
            } else {
                throw NetworkException(resourcesRepository.getNetworkExceptionMessage())
            }
        }
        val body = response.body()!!
        // Only intercept JSON type responses and ignore the rest.
        if (isJsonTypeResponse(body)) {
            val source = body.source()
            source.request(java.lang.Long.MAX_VALUE)
            val buffer = source.buffer()
            val charset = body.contentType()!!.charset(Charset.forName("UTF-8"))!!
            val responseBody = buffer.clone().readString(charset)
            try {

                val apiException = gsonParser.fromJson(responseBody, APIException::class.java)
                if (response.code() == 204) {
                    apiException.message = resourcesRepository.getNoContentErrorMessage()
                    throw apiException
                }
                if (response.code() == 404) {
                    apiException.message = resourcesRepository.getGenericUnknownErrorMessage()
                    throw apiException
                }
                if (apiException.isSucceed != null) {
                    if (response.isSuccessful.not().or(apiException.isSucceed.not())) {
                        throw apiException
                    }
                }
            } catch (e: JsonSyntaxException) {
                throw APIException(
                    isSucceed = false,
                    message = resourcesRepository.getGenericUnknownErrorMessage()
                )
            }
        }
        return response
    }

    private fun isJsonTypeResponse(body: ResponseBody?): Boolean {
        return body?.contentType() != null && body.contentType()!!.subtype().toLowerCase() == keyJson
    }
}