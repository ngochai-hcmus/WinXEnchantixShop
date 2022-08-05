package com.example.winxenchantixshop.Activity.Product

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.winxenchantixshop.Activity.MainActivity
import com.example.winxenchantixshop.databinding.ActivityOrderSucessfullyBinding

class SuccessfulOrderActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOrderSucessfullyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderSucessfullyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.returnButton.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}