package com.example.winxenchantixshop.Activity.Product

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.winxenchantixshop.DTO.Account
import com.example.winxenchantixshop.DTO.User
import com.example.winxenchantixshop.databinding.ActivityShippingAddressBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ShippingAddressActivity : AppCompatActivity() {
    private lateinit var binding : ActivityShippingAddressBinding
    private lateinit var db_ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShippingAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db_ref = FirebaseDatabase.getInstance().getReference("Account")

        binding.btnContinue.setOnClickListener {
            val mail = User.Email("").toString()
            val name = binding.textPersonName.text.toString()
            val phone = binding.textPhone.text.toString()
            val city = binding.textCountry.text.toString()
            val town = binding.textDistrict.text.toString()
            val street = binding.textHouseNum.text.toString()

            if (city.isNotEmpty()) {
                db_ref.child(mail!!.dropLast(10)).child("city").setValue(city)
            }
            if (town.isNotEmpty()) {
                db_ref.child(mail!!.dropLast(10)).child("country").setValue(town)
            }
            if (street.isNotEmpty()) {
                db_ref.child(mail!!.dropLast(10)).child("house").setValue(street)
            }
            if (phone.isNotEmpty()) {
                db_ref.child(mail!!.dropLast(10)).child("phone").setValue(phone)
            }
            if (name.isNotEmpty()) {
                db_ref.child(mail!!.dropLast(10)).child("name").setValue(name)
            }

            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()


            finish()


        }


    }


}