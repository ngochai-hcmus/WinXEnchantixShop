package com.example.winxenchantixshop.Activity.Product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.winxenchantixshop.R
import com.squareup.picasso.Picasso
import com.example.winxenchantixshop.databinding.ActivityProductInformationBinding
import com.example.winxenchantixshop.databinding.ActivityVerifyOtpBinding

class ProductInformationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProductInformationBinding
    private var quantity = 0
    private var imageUrl = ""
    private var productName =""

    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_product_information)

        super.onCreate(savedInstanceState)
        binding = ActivityProductInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        imageUrl = intent.getStringExtra("imageUrl").toString()
        productName = intent.getStringExtra("productName").toString()
        val category = intent.getStringExtra("category")
        val price = intent.getStringExtra("price")
        val amount = intent.getStringExtra("amount")
        val description = intent.getStringExtra("description")

        val imageProduct = binding.imgproduct

        Picasso.get().load(imageUrl).into(imageProduct)

        binding.productname.text = productName
        binding.productprice.text = price
        binding.productdes.text = description
        binding.btnaddcart.setOnClickListener{

        }

    }

    fun checkIsInCart() {

    }

    fun addToCart() {

    }

    fun minusQuantity(view: View) {
        quantity--
        if(quantity < 0){
            quantity = 0
        }
        val textview = findViewById(R.id.number) as TextView
        textview.text = "$quantity"

    }
    fun plusQuantity(view: View) {
        quantity++
        val textview = findViewById(R.id.number) as TextView
        textview.text = "$quantity"
    }


}