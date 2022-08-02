package com.example.winxenchantixshop.Activity.Product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.winxenchantixshop.R
import com.squareup.picasso.Picasso
import com.example.winxenchantixshop.databinding.ActivityProductInformationBinding
import com.example.winxenchantixshop.databinding.ActivityVerifyOtpBinding

class ProductInformationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProductInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_product_information)

        super.onCreate(savedInstanceState)
        binding = ActivityProductInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        val imageUrl = intent.getStringExtra("imageUrl")
        val productName = intent.getStringExtra("productName")
        val category = intent.getStringExtra("category")
        val price = intent.getStringExtra("price")
        val amount = intent.getStringExtra("amount")
        val description = intent.getStringExtra("description")

        val imageProduct = binding.imgproduct

        Picasso.get().load(imageUrl).into(imageProduct)

        binding.productname.text = productName
        binding.productprice.text = price
        binding.productdes.text = description

    }
}