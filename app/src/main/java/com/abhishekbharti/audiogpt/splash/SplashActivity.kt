package com.abhishekbharti.audiogpt.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.abhishekbharti.audiogpt.R
import com.abhishekbharti.audiogpt.databinding.SplashActivityBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var mBinding: SplashActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initClickListener()
    }

    private fun initClickListener(){
        mBinding.apply {
            generateBtn.setOnClickListener {
                Toast.makeText(this@SplashActivity, "generate", Toast.LENGTH_SHORT).show()
            }
        }
    }
}