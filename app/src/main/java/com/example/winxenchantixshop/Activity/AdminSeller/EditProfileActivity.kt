package com.example.winxenchantixshop.Activity.AdminSeller

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.winxenchantixshop.DTO.User
import com.example.winxenchantixshop.DTO.UserInfor
import com.example.winxenchantixshop.databinding.ActivityEditProfileBinding
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditProfileBinding
    private lateinit var db_ref : DatabaseReference
    private lateinit var listUser: ArrayList<UserInfor>
    lateinit var imageUri: Uri

    private val pickImage = 100
    lateinit var imageProduct: ImageView
    lateinit var name: String
    lateinit var age : String
    lateinit var address : String
    lateinit var phone : String
    lateinit var email : String
    lateinit var image : String
    lateinit var type : String
    lateinit var userName: String
    lateinit var userID: String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userName = User.Email("")!!.toString()
        userID = User.Email("")!!.dropLast(10).toString()

        getUserInfor(userName)

        imageProduct = binding.productImg

        binding.btnAddImg.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery,pickImage)
        }


        binding.btnEdit.setOnClickListener {
            name  = binding.textInputLayoutName.editText?.text.toString()
            age  = binding.textInputLayoutAge.editText?.text.toString()
            address  = binding.textInputLayoutAddress.editText?.text.toString()
            phone  = binding.textInputLayoutPhone.editText?.text.toString()
            val newEmail: String = binding.textInputLayoutEmail.editText?.text.toString()
            if(newEmail != email) {
                binding.textInputLayoutEmail.error = "You cannot change your email."
                binding.textInputEditTextEmail.setText(email)
            }

            uploadData()

        }

        binding.btnCancel.setOnClickListener {
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
            this.finish()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data!!
            imageProduct.setImageURI(imageUri)

            uploadImage()
        }

    }


    private fun uploadImage() {
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storage = FirebaseStorage.getInstance().getReference("image/$fileName")

        storage.putFile(imageUri).addOnSuccessListener {
            imageProduct.setImageURI(null)
            Toast.makeText(this, "Successful Uploaded", Toast.LENGTH_SHORT).show()

            val localFile = File.createTempFile(fileName, "")
            storage.getFile(localFile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                imageProduct.setImageBitmap(bitmap)
            }

            storage.downloadUrl.addOnSuccessListener {
                image = it.toString()

            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun uploadData() {
        val user = UserInfor(email, image, name, phone, address, age, type)

        db_ref = FirebaseDatabase.getInstance().getReference("UserInfor")

        db_ref.child(userID).setValue(user).addOnSuccessListener {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }

        val account = mapOf<String,String>(
            "phone" to phone,
            "name" to name
        )

        db_ref = FirebaseDatabase.getInstance().getReference("Account")

        db_ref.child(userID).updateChildren(account).addOnSuccessListener {
            Toast.makeText(this, "Update successful!!!", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(this, "Update Failed!!!", Toast.LENGTH_SHORT).show()
            }

    }


    private fun getUserInfor(name: String) {
        listUser = arrayListOf<UserInfor>()
        db_ref = FirebaseDatabase.getInstance().getReference("UserInfor")
        db_ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listUser.clear()
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(UserInfor::class.java)
                        if (user!!.email.toString() == name){
                            listUser.add(user)
                        }
                    }
                    if(listUser[0].image != "") {
                        Picasso.get().load(listUser[0].image).into(binding.productImg)
                    }
                    image = listUser[0].image.toString()
                    email = listUser[0].email.toString()
                    binding.textInputEditTextName.setText(listUser[0].name)
                    binding.textInputEditTextAge.setText(listUser[0].age)
                    binding.textInputEditTextAddress.setText(listUser[0].address)
                    binding.textInputEditTextPhone.setText(listUser[0].phone)
                    binding.textInputEditTextEmail.setText(listUser[0].email)
                    type = listUser[0].type.toString()
                }
                else{
                    Toast.makeText(applicationContext, "Not Existed", Toast.LENGTH_LONG).show()
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }


}