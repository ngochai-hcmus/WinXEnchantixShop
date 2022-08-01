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

    /*private lateinit var binding : ActivityLoginBinding
    //ActionBar
    private lateinit var actionBar: ActionBar
    //ProgressDialog
    private lateinit var processDialog: ProgressDialog
    //Firebase
    private lateinit var firebaseAuth: FirebaseAuth


    //data
    private var email = ""
    private var password = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSignUp.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.forgotPsw.setOnClickListener{
            startActivity(Intent(this, ForgetPasswordActivity::class.java))
        }



        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()

        checkAccount()
        //handle click, begin login
        binding.loginButton.setOnClickListener {
            //before login
            validateData()

        }

    }
    private fun checkAccount(){
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            startActivity(Intent(this, PostProductActivity::class.java))
            finish()
        }
    }

    private fun validateData(){
        //get data
        email = binding.loginInputField.editText?.text.toString()
        password = binding.loginPswField.editText?.text.toString()

        //validate
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            binding.loginInputField.error = "Invalid email format"
        }
        else if (email.isEmpty()){
            binding.loginPswField.error = "Please enter password"
        }
        else{
            firebaseLogin()
        }


    }
    private fun firebaseLogin(){
        processDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                //login success
                processDialog.dismiss()
                //get user info
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "LoggedIn as $email",Toast.LENGTH_SHORT).show()
                //open home
                startActivity(Intent(this, PostProductActivity::class.java))
                finish()
            }
            .addOnFailureListener { e->
                //login failed
                processDialog.dismiss()
                Toast.makeText(this, "Login failed due to ${e.message}",Toast.LENGTH_SHORT).show()
            }
    }*/

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
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }
    }
}
