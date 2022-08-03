package com.example.winxenchantixshop.Activity.LoginRegister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.winxenchantixshop.databinding.ActivityLoadRegisterBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import android.app.ProgressDialog
import android.content.Intent
import com.example.winxenchantixshop.DTO.Account

class LoadRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadRegisterBinding
    private lateinit var database: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadRegisterBinding.inflate(layoutInflater)
        database = FirebaseDatabase.getInstance().getReference("Account")


        Toast.makeText(this, "Hello 1", Toast.LENGTH_SHORT).show()
        //val email = intent.getStringExtra("Email")
        Toast.makeText(this, "Hello 2", Toast.LENGTH_SHORT).show()
        val type = "Customer"
            val email : String? = intent.getStringExtra("Email")
        val account = Account(email,type)
        Toast.makeText(this, "Mail: $email", Toast.LENGTH_SHORT).show()
        database.child(email.toString()).setValue(account)
            .addOnSuccessListener{
                Toast.makeText(this, "Successful Saved", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

            }

    }
}