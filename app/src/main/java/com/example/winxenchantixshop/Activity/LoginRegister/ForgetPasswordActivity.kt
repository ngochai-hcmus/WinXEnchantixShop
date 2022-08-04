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
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var email: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.forgotPswMailButton.setOnClickListener {
            email =  binding.forgotPswMailInput.editText?.text.toString()
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.forgotPswMailButton.error = "Invalid email format!"
            }
            else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {task->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Successful reset $email", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, LoginActivity::class.java);
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed reset email", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, LoginActivity::class.java);
                        startActivity(intent)
                    }
            }
        }
    }
}