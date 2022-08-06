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
import java.security.Permission
import java.text.SimpleDateFormat
import java.util.*


class EditProductActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditProductBinding
    private lateinit var database : DatabaseReference
    lateinit var imageUri: Uri

    private val RESULT_LOAD_IMAGE = 123
    val IMAGE_CAPTURE_CODE = 654
    lateinit var imageProduct: ImageView
    lateinit var productName : String
    lateinit var name: String
    lateinit var category : String
    lateinit var price : String
    lateinit var amount : String
    lateinit var description : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageProduct = binding.productImg

        init()

        binding.btnEditImg.setOnClickListener{
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE)
        }


        binding.btnEdit.setOnClickListener {
            deleteProduct(name)
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

        val imageUrl = intent.getStringExtra("imageUrl")
        val productName = intent.getStringExtra("productName")
        val category = intent.getStringExtra("category")
        val price = intent.getStringExtra("price")
        val amount = intent.getStringExtra("amount")
        val description = intent.getStringExtra("description")

        Picasso.get().load(imageUrl).into(imageProduct)

        name = productName.toString()

        binding.textInputEditTextProductName.setText(productName)
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
        if (requestCode == IMAGE_CAPTURE_CODE && resultCode == Activity.RESULT_OK) {
            imageProduct!!.setImageURI(imageUri)
            val bitmap = uriToBitmap(imageUri!!)
            imageProduct!!.setImageBitmap(bitmap)
        }
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data?.data!!
            imageProduct!!.setImageURI(imageUri)
        }
    }

    private fun uriToBitmap(selectedFileUri: Uri): Bitmap? {
        try {
            val parcelFileDescriptor = contentResolver.openFileDescriptor(selectedFileUri, "r")
            val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
            val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()
            return image
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
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

//    private object COMPARATOR: DiffUtil.ItemCallback<Product>() {
//
//        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
//            return oldItem == newItem
//        }
//
//        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
//            return oldItem.productName == newItem.productName
//        }
//    }
}