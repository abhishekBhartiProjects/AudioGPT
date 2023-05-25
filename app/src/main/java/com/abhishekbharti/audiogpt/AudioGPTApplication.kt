package com.abhishekbharti.audiogpt

import android.app.Application
import com.abhishekbharti.audiogpt.network.APIInterface
import com.abhishekbharti.audiogpt.network.APIObject

class AudioGPTApplication: Application() {

    companion object {
        @Volatile
        private var instance: AudioGPTApplication? = null
        @Synchronized
        fun getInstance(): AudioGPTApplication = instance ?: AudioGPTApplication()
    }

    @Volatile
    private var apiInterface: APIInterface? = null

    override fun onCreate() {
        super.onCreate()
    }

    @Synchronized
    fun getAPIInterface(): APIInterface = apiInterface ?: APIObject.build()
}