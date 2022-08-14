package com.example.winxenchantixshop.Activity.Product
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.databinding.ActivityEditProductBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileDescriptor
import java.io.IOException
import java.net.URL
import java.security.Permission
import java.text.SimpleDateFormat
import java.util.*


class EditProductActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditProductBinding
    private lateinit var database : DatabaseReference
    lateinit var imageUri: Uri

    private val pickImage = 100
    lateinit var imageProduct: ImageView
    lateinit var productName : String
    lateinit var name: String
    lateinit var category : String
    lateinit var price : String
    lateinit var amount : String
    lateinit var description : String
    lateinit var imageUrl : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageProduct = binding.productImg

        init()

        binding.btnEditImg.setOnClickListener{
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery,pickImage)
        }

        binding.btnEdit.setOnClickListener {
            productName  = binding.textInputLayoutProductName.editText?.text.toString()
            category  = binding.textInputLayoutCategory.editText?.text.toString()
            price  = binding.textInputLayoutPrice.editText?.text.toString()
            amount  = binding.textInputLayoutAmount.editText?.text.toString()
            description  = binding.textInputLayoutDescription.editText?.text.toString()
            uploadData()
        }

        binding.btnCancel.setOnClickListener {
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
            this.finish()
        }

    }

    private fun init() {
        database = FirebaseDatabase.getInstance().getReference("Product")

        val intent = intent

        imageUrl = intent.getStringExtra("imageUrl").toString()
        name = intent.getStringExtra("productName").toString()
        category = intent.getStringExtra("category").toString()
        price = intent.getStringExtra("price").toString()
        amount = intent.getStringExtra("amount").toString()
        description = intent.getStringExtra("description").toString()

        Picasso.get().load(imageUrl).into(imageProduct)

        binding.textInputEditTextProductName.setText(name)
        binding.textInputEditTextCategory.setText(category)
        binding.textInputEditTextPrice.setText(price)
        binding.textInputEditTextAmount.setText(amount)
        binding.textInputEditTextDescription.setText(description)
    }

    private fun deleteProduct(productName: String) {
        database = FirebaseDatabase.getInstance().getReference("Product")

        database.child(productName).removeValue().addOnSuccessListener {
            //Toast.makeText(applicationContext, "Deleted 1 item", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            //Toast.makeText(applicationContext, "Failed to delete, try again", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
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
                imageUrl = it.toString()

            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun uploadData() {
        val product = Product(imageUrl, productName, category, price, amount, description)

        database.child(productName).setValue(product).addOnSuccessListener {
            deleteProduct(name)
            Toast.makeText(this, "Successful Saved", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

        }

    }

}