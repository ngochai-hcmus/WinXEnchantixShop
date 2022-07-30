package com.example.winxenchantixshop.Activity.LoginRegister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.winxenchantixshop.databinding.ActivityForgetPasswordBinding
import com.example.winxenchantixshop.databinding.ActivityVerifyOtpBinding

class VerifyOTPActivity : AppCompatActivity() {
    private lateinit var binding : ActivityVerifyOtpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.forgotPswCodeButton.setOnClickListener {
            startActivity(Intent(this, ForgetPasswordActivity::class.java))
        }
    }
}