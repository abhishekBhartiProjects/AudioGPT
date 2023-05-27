package com.abhishekbharti.audiogpt.base

import com.abhishekbharti.audiogpt.AudioGPTApplication
import com.abhishekbharti.audiogpt.network.RequestResult
import retrofit2.Response

open class BaseRepo {
    val mApplication = AudioGPTApplication.getInstance()
    val apiInterface = mApplication.getAPIInterface()

    suspend fun <T, R> handleCommonResponse(
        requestFunc: suspend () -> Response<T>,
        successCallback: (Response<T>) -> RequestResult.Success<R>
    ): RequestResult<R> {

        try {
            val response = requestFunc.invoke()
            return if (response.isSuccessful) {
                successCallback(response)
            } else {
                RequestResult.ApiError(response.code(), response.message())
            }
        } catch (e: Exception) {
            return RequestResult.OtherError(e)
        }
    }
}