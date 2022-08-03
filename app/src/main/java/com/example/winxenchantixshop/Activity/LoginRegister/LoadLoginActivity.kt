package com.example.winxenchantixshop.Activity.LoginRegister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.winxenchantixshop.databinding.ActivityLoadRegisterBinding

class LoadLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityLoadRegisterBinding.inflate(layoutInflater)
    }
}