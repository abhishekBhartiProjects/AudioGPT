package com.abhishekbharti.audiogpt.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels // dependency -> api 'androidx.activity:activity-ktx:1.7.2'
import com.abhishekbharti.audiogpt.databinding.SplashActivityBinding
import com.abhishekbharti.audiogpt.network.RequestResult
import com.abhishekbharti.audiogpt.response.CompletionResponse

class SplashActivity : AppCompatActivity() {

    private lateinit var mBinding: SplashActivityBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initClickListener()
        initViewModelObserver()
    }

    private fun initClickListener(){
        mBinding.apply {
            generateBtn.setOnClickListener {
                val prompt = promptEt.text.toString()
                if(prompt.isNotEmpty()){
                    viewModel.postPrompt(prompt)
                } else {
                    Toast.makeText(this@SplashActivity, "Plz write something", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun initViewModelObserver(){
        viewModel.promptResponseMLD.observe(this){
            when(it) {
                is RequestResult.Loading -> {}
                is RequestResult.Success -> {
                    onGetPromptResponse(it.data as CompletionResponse)
                }
                is RequestResult.ApiError -> {}
                is RequestResult.OtherError -> {}
                else -> {}
            }
        }
    }

    private fun onGetPromptResponse(response: CompletionResponse){
        mBinding.apply {
            responseTv.text = response.choices.get(0).text
        }
    }
}