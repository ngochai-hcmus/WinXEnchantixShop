package com.example.winxenchantixshop.DAO

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import com.example.winxenchantixshop.DTO.Category

class CategoryDAO {
    private val linkConnect = "https://winxenchantixshop-c3794-default-rtdb.firebaseio.com/"
    private val myDatabase : DatabaseReference = FirebaseDatabase.getInstance(linkConnect).getReference("Product")

    private fun addOrUpdate(categoryID: String, category: Category) {
        myDatabase.child(categoryID).setValue(category)
    }

    private fun delete(categoryID: String) {
        myDatabase.child(categoryID).removeValue().addOnSuccessListener {
            Log.d("TAG", "Category data deleted")
        }.addOnFailureListener {
            Log.e("TAG", "Delete Error")
        }
    }
}