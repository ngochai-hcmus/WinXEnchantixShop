package com.example.winxenchantixshop.Activity.Product

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.winxenchantixshop.databinding.ActivityPostProductBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

import com.example.winxenchantixshop.DTO.Product


class PostProductActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPostProductBinding
    private lateinit var database : DatabaseReference
    lateinit var imageUri: Uri

    private val pickImage = 100
    lateinit var imageProduct: ImageView

    lateinit var productName : String
    lateinit var category : String
    lateinit var price : String
    lateinit var amount : String
    lateinit var description : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Product")

        imageProduct = binding.productImg

        binding.btnAddImg.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery,pickImage)
            //binding.btnAddImg.hide()
        }

        binding.btnPost.setOnClickListener{
            productName  = binding.textInputLayoutProductName.editText?.text.toString()
            category  = binding.textInputLayoutCategory.editText?.text.toString()
            price  = binding.textInputLayoutPrice.editText?.text.toString()
            amount  = binding.textInputLayoutAmount.editText?.text.toString()
            description  = binding.textInputLayoutDescription.editText?.text.toString()

            uploadData()

        }

        binding.btnCancel.setOnClickListener {
            Toast.makeText(this, "Cancel",Toast.LENGTH_SHORT).show()
            this.finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data!!
            imageProduct.setImageURI(imageUri)
        }
    }

    private fun uploadData() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading File ...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storage = FirebaseStorage.getInstance().getReference("image/$fileName")

        storage.putFile(imageUri).addOnSuccessListener {
            imageProduct.setImageURI(null)
            Toast.makeText(this, "Successful Uploaded", Toast.LENGTH_SHORT).show()
            if (progressDialog.isShowing) progressDialog.dismiss()

            val localFile = File.createTempFile(fileName,"")
            storage.getFile(localFile).addOnSuccessListener {
                if(progressDialog.isShowing)
                    progressDialog.dismiss()

                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                imageProduct.setImageBitmap(bitmap)

            }

            storage.downloadUrl.addOnSuccessListener {
                val product = Product(it.toString(), productName, category, price, amount, description)

                database.child(productName.toString()).setValue(product).addOnSuccessListener {
                    Toast.makeText(this, "Successful Saved", Toast.LENGTH_SHORT).show()

                }.addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

                }
            }.addOnFailureListener {
                if (progressDialog.isShowing) progressDialog.dismiss()
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

            }


        }
    }


}