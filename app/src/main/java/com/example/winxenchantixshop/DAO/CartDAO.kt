package com.example.winxenchantixshop.DAO

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import com.example.winxenchantixshop.DTO.Cart

class CartDAO {
    private val linkConnect = "https://winxenchantixshop-c3794-default-rtdb.firebaseio.com/"
    private val myDatabase : DatabaseReference = FirebaseDatabase.getInstance(linkConnect).getReference("Cart")

    private fun addOrUpdate(cartID: String, cart: Cart) {
        myDatabase.child(cartID).setValue(cart)
    }

    private fun delete(cartID: String) {
        myDatabase.child(cartID).removeValue().addOnSuccessListener {
            Log.d("TAG", "Cart data deleted")
        }.addOnFailureListener {
            Log.e("TAG", "Delete Error")
        }
    }
}