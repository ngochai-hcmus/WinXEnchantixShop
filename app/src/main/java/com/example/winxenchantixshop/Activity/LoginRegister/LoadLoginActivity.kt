package com.example.winxenchantixshop.Activity.LoginRegister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.winxenchantixshop.databinding.ActivityLoadRegisterBinding
import com.example.winxenchantixshop.DTO.Account
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import android.content.Intent
import android.app.ProgressDialog
import com.example.winxenchantixshop.Activity.AdminSeller.Admin_seller_homeActivity
import com.example.winxenchantixshop.Activity.AdminSeller.ConversionActivity
import com.example.winxenchantixshop.Activity.MainActivity
import com.example.winxenchantixshop.Activity.Product.PostProductActivity

class LoadLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadRegisterBinding
    private lateinit var database: DatabaseReference

    lateinit var n : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance().getReference("Account")

        val email = intent.getStringExtra("Email")
        n = email.toString()
        val nchild = n.subSequence(0,n.length-10).toString()

        database.child(nchild.toString()).get().addOnSuccessListener {
            if(it.exists()){
                Toast.makeText(this, "Login successful!!!", Toast.LENGTH_SHORT).show()
                val type = it.child("type").value
                //Toast.makeText(this, type.toString(), Toast.LENGTH_SHORT).show()
                if (type.toString() == "customer")
                {
                    Toast.makeText(this, "User is customer!!!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java);
                    intent.putExtra("EmailUser",email.toString())
                    startActivity(intent)
                }

                else if (type.toString() == "seller"){
                    Toast.makeText(this, "User is seller!!!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Admin_seller_homeActivity::class.java);
                    intent.putExtra("EmailUser",email.toString())
                    startActivity(intent)
                }
                else if (type.toString() == "admin")
                {
                    Toast.makeText(this, "User is Admin!!!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Admin_seller_homeActivity::class.java);
                    intent.putExtra("EmailUser",email.toString())
                    startActivity(intent)
                }
            }
            else
            {
                Toast.makeText(this, "User is not exists in database. Please login again!!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java);
                startActivity(intent)
            }
        }
            .addOnFailureListener {
                Toast.makeText(this, "Failed!!!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java);
                startActivity(intent)
            }
    }
}

