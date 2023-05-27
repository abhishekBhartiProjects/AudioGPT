package com.abhishekbharti.audiogpt.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abhishekbharti.audiogpt.base.BaseViewModel
import com.abhishekbharti.audiogpt.network.RequestResult
import kotlinx.coroutines.launch
import java.lang.Exception

class SplashViewModel: BaseViewModel() {
    private val repo = SplashRepo()

    var promptResponseMLD: MutableLiveData<RequestResult<Any?>> = MutableLiveData()

    fun postPrompt(prompt: String){
        viewModelScope.launch(exceptionHandler){
            try{
                promptResponseMLD.value = RequestResult.Loading("")
                val result = repo.postPrompt(prompt)
                promptResponseMLD.value = result
            } catch (e: Exception){
                promptResponseMLD.value = RequestResult.OtherError(e)
            }
        }
    }
}