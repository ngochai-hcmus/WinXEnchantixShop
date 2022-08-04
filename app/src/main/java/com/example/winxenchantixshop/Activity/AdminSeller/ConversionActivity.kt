package com.example.winxenchantixshop.Activity.AdminSeller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.winxenchantixshop.DTO.Account
import com.example.winxenchantixshop.databinding.ActivityTurnIntoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ConversionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTurnIntoBinding
    private lateinit var database: DatabaseReference

    lateinit var n : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTurnIntoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBecomeSeller.setOnClickListener {
            val email = binding.inputUserName.editText?.text.toString()
            n = email.toString()
            val user_email = n.subSequence(0,n.length-10).toString()
            updateData(user_email)
        }
    }

    private fun updateData(userEmail: String) {
        database = FirebaseDatabase.getInstance().getReference("Account")
        val account = mapOf<String,String>(
            "type" to "seller"
        )
        database.child(userEmail).updateChildren(account).addOnSuccessListener {
            Toast.makeText(this, "Update successful!!!", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(this, "Update Failed!!!", Toast.LENGTH_SHORT).show()
            }
    }
}