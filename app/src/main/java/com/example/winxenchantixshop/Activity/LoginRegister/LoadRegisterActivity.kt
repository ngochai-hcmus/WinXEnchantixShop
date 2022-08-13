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
import com.example.winxenchantixshop.Activity.MainActivity
import com.example.winxenchantixshop.Activity.Product.PostProductActivity
import com.example.winxenchantixshop.DTO.Account

class LoadRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadRegisterBinding
    private lateinit var database: DatabaseReference

    lateinit var n : String
    lateinit var type : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Account")

        val email = intent.getStringExtra("Email")
        type = "customer"
        n = email.toString()

        val nchild = n.subSequence(0,n.length-10).toString()
        val account = Account(n.toString(),type,nchild,"","","")
        database.child(nchild.toString()).setValue(account).addOnSuccessListener {
            Toast.makeText(this, "Successful Saved", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
            .addOnFailureListener {
                Toast.makeText(this, "Failed Saved", Toast.LENGTH_SHORT).show()
            }
    }
}