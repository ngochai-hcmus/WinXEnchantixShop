package com.example.winxenchantixshop.DAO

import androidx.lifecycle.MutableLiveData
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.DTO.UserInfor
import com.google.firebase.database.*

class UserInforDAO {
    private val databaseRef : DatabaseReference = FirebaseDatabase.getInstance().getReference("UserInfor")

    @Volatile private var INSTANCE : UserInforDAO ?= null

    fun getInstance() : UserInforDAO{
        return INSTANCE ?: synchronized(this){

            val instance = UserInforDAO()
            INSTANCE = instance
            instance
        }
    }


    fun loadUsers(userList : MutableLiveData<List<UserInfor>>){

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                try {

                    val _userList : List<UserInfor> = snapshot.children.map { dataSnapshot ->

                        dataSnapshot.getValue(UserInfor::class.java)!!

                    }

                    userList.postValue(_userList)

                }catch (e : Exception){

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}