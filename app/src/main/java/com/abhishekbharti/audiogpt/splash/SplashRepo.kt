package com.abhishekbharti.audiogpt.splash

import com.abhishekbharti.audiogpt.base.BaseRepo
import com.abhishekbharti.audiogpt.network.RequestResult
import com.abhishekbharti.audiogpt.requestBody.CompletionRequestBody
import com.abhishekbharti.audiogpt.response.CompletionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SplashRepo: BaseRepo() {

    suspend fun postPrompt(prompt:String): RequestResult<CompletionResponse?>{
        return withContext(Dispatchers.IO){
            return@withContext handleCommonResponse(
                {
                    val body = CompletionRequestBody(
                        frequency_penalty = 0,
                        max_tokens = 4000,
                        model = "text-davinci-003",
                        presence_penalty = 0,
                        prompt = prompt,
                        temperature = 0.7F,
                        top_p = 1
                    )
                    apiInterface.postPrompt(body)
                },
                {
                    RequestResult.Success(it.body())
                }
            )
        }

    }
}