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
import com.example.winxenchantixshop.DTO.UserInfor

class LoadRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadRegisterBinding
    private lateinit var database: DatabaseReference
    private lateinit var database2 : DatabaseReference

    lateinit var n : String
    lateinit var type : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Account")

        database2 = FirebaseDatabase.getInstance().getReference("UserInfor")

        val email = intent.getStringExtra("Email")
        type = "customer"
        n = email.toString()

        val nchild = n.subSequence(0,n.length-10).toString()
        val account = Account(n.toString(),type,nchild,"","","")


        val userinfor = UserInfor(n,"",nchild,"","","",type)
        database2.child(nchild.toString()).setValue((userinfor)).addOnSuccessListener {
            Toast.makeText(this, "Successful Saved user info", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener{
                Toast.makeText(this, "Failed Saved user info", Toast.LENGTH_SHORT).show()
            }


        database.child(nchild.toString()).setValue(account).addOnSuccessListener {
            Toast.makeText(this, "Successful Saved account", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
            .addOnFailureListener {
                Toast.makeText(this, "Failed Saved account", Toast.LENGTH_SHORT).show()
            }


    }
}