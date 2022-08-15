package com.example.winxenchantixshop.DAO

import android.util.Log
import androidx.lifecycle.MutableLiveData

import com.example.winxenchantixshop.DTO.Product
import com.google.firebase.database.*

class ProductDAO {
    private val databaseRef : DatabaseReference = FirebaseDatabase.getInstance().getReference("Product")

    public fun addOrUpdate(productID: String, product: Product) {
        databaseRef.child(productID).setValue(product)
    }

    public fun delete(productID: String) {
        databaseRef.child(productID).removeValue().addOnSuccessListener {
            Log.d("TAG", "Product data deleted")
        }.addOnFailureListener {
            Log.e("TAG", "Delete Error")
        }
    }

    @Volatile private var INSTANCE : ProductDAO ?= null

    fun getInstance() : ProductDAO{
        return INSTANCE ?: synchronized(this){

            val instance = ProductDAO()
            INSTANCE = instance
            instance
        }
    }


    fun loadProducts(productList : MutableLiveData<List<Product>>){

        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                try {

                    val _productList : List<Product> = snapshot.children.map { dataSnapshot ->

                        dataSnapshot.getValue(Product::class.java)!!

                    }

                    productList.postValue(_productList)

                }catch (e : Exception){

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}