package com.example.winxenchantixshop.Activity.AdminSeller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Adapter.ProductAdapter
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.DTO.ProductView
import com.example.winxenchantixshop.databinding.ActivityListCustomerBinding

class ListCustomerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}