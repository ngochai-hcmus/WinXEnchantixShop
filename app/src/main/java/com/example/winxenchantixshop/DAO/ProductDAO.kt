package com.example.winxenchantixshop.DAO

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import com.example.winxenchantixshop.DTO.Product

class ProductDAO {
    private val linkConnect = "https://winxenchantixshop-c3794-default-rtdb.firebaseio.com/"
    private val myDatabase : DatabaseReference = FirebaseDatabase.getInstance(linkConnect).getReference("Product")

    public fun addOrUpdate(productID: String, product: Product) {
        myDatabase.child(productID).setValue(product)
    }

    public fun delete(productID: String) {
        myDatabase.child(productID).removeValue().addOnSuccessListener {
            Log.d("TAG", "Product data deleted")
        }.addOnFailureListener {
            Log.e("TAG", "Delete Error")
        }
    }
}