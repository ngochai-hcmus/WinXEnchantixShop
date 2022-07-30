package com.example.winxenchantixshop.DAO

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import com.example.winxenchantixshop.DTO.Account

class AccountDAO {
    private val linkConnect = "https://winxenchantixshop-c3794-default-rtdb.firebaseio.com/"
    private val myDatabase : DatabaseReference = FirebaseDatabase.getInstance(linkConnect).getReference("Account")

    private fun addOrUpdate(accountID: String, account: Account) {
        myDatabase.child(accountID).setValue(account)
    }

    private fun delete(accountID: String) {
        myDatabase.child(accountID).removeValue().addOnSuccessListener {
            Log.d("TAG", "Account data deleted")
        }.addOnFailureListener {
            Log.e("TAG", "Delete Error")
        }
    }
}