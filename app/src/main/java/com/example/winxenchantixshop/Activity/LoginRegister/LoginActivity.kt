package com.example.winxenchantixshop.Activity.LoginRegister

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.winxenchantixshop.databinding.ActivityLoginBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.auth.FirebaseAuth
import android.text.TextUtils;
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.winxenchantixshop.Activity.MainActivity
import com.example.winxenchantixshop.Activity.Product.PostProductActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }
        binding.loginButton.setOnClickListener {
            val email = binding.loginInputField.editText?.text.toString()
            val pass = binding.loginPswField.editText?.text.toString()

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.loginInputField.error = "Invalid email format!"
            }
            else if (pass.isEmpty()){
                binding.loginPswField.error = "Please enter your password!"
            }
            else if (pass.length < 6)
            {
                binding.loginPswField.error = "Password must be more than 6 characters!"
            }
            else{
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        val intent = Intent(this, LoadLoginActivity::class.java);
                        intent.putExtra("Email",email.toString())
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }

        binding.forgotPsw.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java);
            startActivity(intent)
        }
    }
}
