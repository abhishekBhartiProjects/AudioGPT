package com.abhishekbharti.audiogpt.network

import com.abhishekbharti.audiogpt.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIObject {

    private const val CONTENT_TYPE = "content-type"
    private const val CONTENT_TYPE_VALUE = "application/json"

    fun build(): APIInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openai.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()

        return retrofit.create(APIInterface::class.java)
    }

    private fun okHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.interceptors().add(interceptor(0))
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        if(BuildConfig.DEBUG)
            httpClient.interceptors().add(httpLoggingInterceptor())

        return httpClient.build()
    }

    private fun interceptor(cacheDuration: Long): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            requestBuilder.addHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE)
            requestBuilder.addHeader("Authorization", "Bearer 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")

            //easy Tuto
//            requestBuilder.addHeader("Authorization", "Bearer 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")
            if (cacheDuration > 0) {
                requestBuilder.addHeader("Cache-Control", "public, max-age=$cacheDuration")
            }
            requestBuilder.addHeader("app-version", BuildConfig.VERSION_CODE.toString())

            val request = requestBuilder.build()
            val response = chain.proceed(request)
            response
        }
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}