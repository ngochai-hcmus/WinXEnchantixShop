package com.example.winxenchantixshop.Activity.AdminSeller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.winxenchantixshop.databinding.ActivityUserProfileBinding
import com.squareup.picasso.Picasso

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        val image = intent.getStringExtra("image")
        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        val address = intent.getStringExtra("address")
        val phone = intent.getStringExtra("phone")
        val email = intent.getStringExtra("email")

        val imageProduct = binding.productImg
        if(image != ""){
            Picasso.get().load(image).into(imageProduct)
        }

        binding.textName.text = name
        binding.textAge.text = age
        binding.textAddress.text = address
        binding.textPhone.text = phone
        binding.textEmail.text = email

    }
}