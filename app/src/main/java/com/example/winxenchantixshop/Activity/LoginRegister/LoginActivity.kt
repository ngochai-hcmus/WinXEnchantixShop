package com.example.winxenchantixshop.Activity.LoginRegister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.winxenchantixshop.databinding.ActivityLoginBinding
import com.example.winxenchantixshop.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSignUp.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.forgotPsw.setOnClickListener{
            startActivity(Intent(this, ForgetPasswordActivity::class.java))
        }
    }
}