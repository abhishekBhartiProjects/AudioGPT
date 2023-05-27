package com.abhishekbharti.audiogpt.network

import com.abhishekbharti.audiogpt.requestBody.CompletionRequestBody
import com.abhishekbharti.audiogpt.response.CompletionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface APIInterface {

    @POST("v1/completions")
    suspend fun postPrompt(
        @Body completionRequestBody: CompletionRequestBody
    ): Response<CompletionResponse>


}