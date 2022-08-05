package com.example.winxenchantixshop.Activity.AdminSeller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.winxenchantixshop.databinding.ItemNotifyBinding

class NewOrderActivity : AppCompatActivity() {
    private lateinit var binding: ItemNotifyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemNotifyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}