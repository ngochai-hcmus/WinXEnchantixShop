package com.example.winxenchantixshop.DAO

import android.util.Log
import androidx.lifecycle.MutableLiveData

import com.example.winxenchantixshop.DTO.Account
import com.example.winxenchantixshop.DTO.Product
import com.google.firebase.database.*

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

