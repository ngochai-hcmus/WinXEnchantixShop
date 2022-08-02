package com.example.winxenchantixshop.Activity.LoginRegister

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar

import com.example.winxenchantixshop.databinding.ActivityRegisterBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.auth.FirebaseAuth

import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
   /* //ViewBinding
    private lateinit var binding: ActivityRegisterBinding
    //ActionBar
    private lateinit var actionBar: ActionBar
    //ProgressDialog
    private lateinit var processDialog: ProgressDialog
    //Firebase
    private lateinit var firebaseAuth: FirebaseAuth

    //data
    private var email = ""
    private var password = ""
    private var type = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()

        //handle click, begin register
        binding.registerButton.setOnClickListener {
            validateData()
        }

        binding.btnSignIn.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }
    private fun validateData(){
        email = binding.registerInputFullName.editText?.text.toString()
        password = binding.registerPswField.editText?.text.toString()
        type = binding.registerInputEmail.editText?.text.toString()

        //validate data
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ){
            binding.registerInputFullName.error = "Invalid email format"
        }
        else if (password.isEmpty()){
            binding.registerPswField.error = "Please enter your password"
            }
        else if (password.length < 6)
        {
            binding.registerPswField.error = "Password must be more than 6 characters"
        }
        else
        {
            firebaseRegister()
        }
    }
    private fun firebaseRegister()
    {
        processDialog.show()

        //create account
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
            //Register success
                processDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Account create with email $email",Toast.LENGTH_SHORT).show()

            //Open login
            startActivity(Intent(this,LoginActivity::class.java))
            finish()


        }.addOnFailureListener {e->
                //Register failed
            processDialog.dismiss()
                Toast.makeText(this,"Register Failed due to ${e.message}",Toast.LENGTH_SHORT).show()
            }
    }*/

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
            binding.registerButton.setOnClickListener {
                val email = binding.registerInputEmail.editText?.text.toString()
                val pass = binding.registerPassword.editText?.text.toString()
                val confirmPass = binding.registerConfirmPassword.editText?.text.toString()
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
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if(it.isSuccessful)
                        {
                            val intent = Intent(this, LoginActivity::class.java)
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

