package com.example.winxenchantixshop.Activity.LoginRegister

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar

import com.example.winxenchantixshop.databinding.ActivityRegisterBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.example.winxenchantixshop.Activity.Product.PostProductActivity
import com.example.winxenchantixshop.DTO.Account


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    lateinit var email : String
    lateinit var pass : String
    lateinit var confirmPass : String
    lateinit var type : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textAlreadyUser.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
            binding.registerButton.setOnClickListener {
                email = binding.registerInputEmail.editText?.text.toString()
                pass = binding.registerPassword.editText?.text.toString()
                confirmPass = binding.registerConfirmPassword.editText?.text.toString()
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    binding.registerInputEmail.error = "Invalid email format!"
                }
                else if (pass.isEmpty()){
                    binding.registerPassword.error = "Please enter your password!"
                }
                else if (pass.length < 6)
                {
                    binding.registerInputEmail.error = "Password must be more than 6 characters!"
                }
                else if (pass != confirmPass)
                {
                    binding.registerConfirmPassword.error = "confirm password does not match password!"
                }
                else{
                    firebaseAuth = FirebaseAuth.getInstance()
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) { task->
                        if(task.isSuccessful){
                            //move to login
                            Toast.makeText(this, "Successful create account", Toast.LENGTH_SHORT).show()
                            var intent = Intent(this, LoginActivity::class.java)
                            intent.putExtra("Email",email.toString())
                            startActivity(intent)
                        }
                        else
                        {
                            Toast.makeText(this,"failed create email!!!",Toast.LENGTH_SHORT).show()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(this,"failed create email!!!",Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}


