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



class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.textAlreadyUser.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.registerButton.setOnClickListener {
           val email:String = binding.registerInputEmail.editText?.text.toString()
           val pass:String = binding.registerPassword.editText?.text.toString()
           val confirmPass:String = binding.registerConfirmPassword.editText?.text.toString()
            if (/*!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()*/ email.isEmpty()){
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
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) { task->
                    if(task.isSuccessful){
                        //move to login
                        Toast.makeText(this, "Successful create account $email", Toast.LENGTH_SHORT).show()
                        val helo = "hello@gmail.com"
                        val hehe = helo
                        val intent = Intent(this, LoadRegisterActivity::class.java);
                        intent.putExtra("Email",hehe.toString())
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


