package com.example.winxenchantixshop.DTO

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import java.lang.Exception

class AccountRepsitory {

    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Account")

    @Volatile private var INSTANCE : AccountRepsitory ?= null

    fun getInstance() : AccountRepsitory{
        return INSTANCE ?: synchronized(this){
            val instance = AccountRepsitory()
            INSTANCE = instance
            instance
        }

    }

    fun loadAccount(AccountList : MutableLiveData<List<Account>>){

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                try{
                    val _AccountList : List<Account> = snapshot.children.map { dataSnapshot ->

                        dataSnapshot.getValue(Account::class.java)!!
                    }

                    AccountList.postValue(_AccountList)

                }catch (e : Exception){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}