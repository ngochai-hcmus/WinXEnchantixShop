package com.example.winxenchantixshop.Activity.AdminSeller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.winxenchantixshop.databinding.FragmentHomePageSellerBinding

class Admin_seller_homeActivity : AppCompatActivity() {

    private lateinit var binding: FragmentHomePageSellerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomePageSellerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutMyproduct.setOnClickListener {

        }
        binding.layoutPostProduct.setOnClickListener {

        }
        binding.layoutNewOrder.setOnClickListener {

        }
        binding.layoutOrderHistory.setOnClickListener {

        }
        binding.layoutListReview.setOnClickListener {

        }
        binding.layoutReplyReview.setOnClickListener {

        }
        binding.layoutListSeller.setOnClickListener {

        }
        binding.layoutListCustomer.setOnClickListener {

        }
    }
}