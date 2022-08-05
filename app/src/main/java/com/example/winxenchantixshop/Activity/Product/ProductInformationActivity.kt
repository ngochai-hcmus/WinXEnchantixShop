package com.example.winxenchantixshop.Activity.Product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.winxenchantixshop.Activity.MainActivity
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.DTO.User
import com.example.winxenchantixshop.R
import com.squareup.picasso.Picasso
import com.example.winxenchantixshop.databinding.ActivityProductInformationBinding
import com.example.winxenchantixshop.databinding.ActivityVerifyOtpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProductInformationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProductInformationBinding
    private var listProduct = ArrayList<Product>()
    private lateinit var db_ref : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_product_information)

        super.onCreate(savedInstanceState)
        binding = ActivityProductInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db_ref = FirebaseDatabase.getInstance().getReference("Cart")


        val intent = intent

        val imageUrl = intent.getStringExtra("imageUrl")
        val productName = intent.getStringExtra("productName")
        val category = intent.getStringExtra("category")
        val price = intent.getStringExtra("price")
        val description = intent.getStringExtra("description")

        val imageProduct = binding.imgproduct

        Picasso.get().load(imageUrl).into(imageProduct)

        binding.productname.text = productName
        binding.productprice.text = price
        binding.productdes.text = description

        binding.btnplus.setOnClickListener {
            increaseQuantity()
        }

        binding.btnminus.setOnClickListener {
            decreaseQuantity()
        }

        binding.btnbuy.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            val product = Product(imageUrl, productName, category, price, binding.number.text.toString(), description)
            listProduct.clear()
            listProduct.add(product)
            intent.putExtra("listProduct", listProduct)
            startActivity(intent)
        }

        binding.btnaddcart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            val product = Product(imageUrl, productName, category, price, binding.number.text.toString(), description)
            val userName = User.Email("")!!.dropLast(10).toString()

            if (binding.number.text.toString() != "0") {
                db_ref.child(userName).child(productName.toString()).setValue(product)
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Can not add empty product", Toast.LENGTH_SHORT).show()
            }

        }

        binding.imgcart.setOnClickListener{
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

    }

    private fun increaseQuantity(){
        val amount = binding.number.text.toString().toInt()
        binding.number.text = amount.inc().toString()
    }

    private fun decreaseQuantity(){
        val amount = binding.number.text.toString().toInt()
        if (amount == 0) {
            binding.number.text = "0"
        }
        else {
            binding.number.text = amount.dec().toString()
        }

    }

}