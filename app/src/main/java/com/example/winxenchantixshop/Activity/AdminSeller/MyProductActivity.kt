package com.example.winxenchantixshop.Activity.AdminSeller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.winxenchantixshop.databinding.ActivityMyProductBinding

class MyProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}