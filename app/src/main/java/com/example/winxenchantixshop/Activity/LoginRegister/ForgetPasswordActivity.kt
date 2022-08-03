package com.example.winxenchantixshop.Activity.LoginRegister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.winxenchantixshop.DTO.Account
import com.example.winxenchantixshop.databinding.ActivityForgetPasswordBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.auth.FirebaseAuth
class ForgetPasswordActivity : AppCompatActivity() {
    private lateinit var binding : ActivityForgetPasswordBinding
    private lateinit var database: DatabaseReference

    lateinit var email : String
    lateinit var type : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Account")

        binding.forgotPswMailButton.setOnClickListener {
            email =  binding.forgotPswMailInput.editText?.text.toString()
            val account = Account(email.toString(),"customer")
            type = "Customer"
            database.child(email.toString()).setValue(account).addOnSuccessListener {
                Toast.makeText(this, "Successful Saved", Toast.LENGTH_SHORT).show()


            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

            }




        }
    }
}