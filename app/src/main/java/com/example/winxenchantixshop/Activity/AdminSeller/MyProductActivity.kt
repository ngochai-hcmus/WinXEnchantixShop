package com.example.winxenchantixshop.Activity.AdminSeller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Activity.Util.SwipeToDeleteCallback
import com.example.winxenchantixshop.Adapter.ItemAdapter
import com.example.winxenchantixshop.Adapter.ProductAdapter
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.databinding.ActivityMyProductBinding
import com.google.firebase.database.*

class MyProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyProductBinding
    private lateinit var productAdapter : ProductAdapter
    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var db_ref : DatabaseReference
    private lateinit var listProduct: ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProductBinding.inflate(layoutInflater)
        setContentView(binding.root)


        itemRecyclerView = binding.recycleMyProduct
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemRecyclerView.setHasFixedSize(true)
        listProduct = arrayListOf<Product>()
        productAdapter = ProductAdapter(listProduct)
        getProduct()

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos : Int = viewHolder.adapterPosition
                val productToDelete : Product = listProduct.get(pos)
                val productName = productToDelete.productName.toString()
                deleteProduct(productName)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(itemRecyclerView)

    }

    private fun getProduct() {
        listProduct = arrayListOf<Product>()
        db_ref = FirebaseDatabase.getInstance().getReference("Product")
        db_ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (productSnapshot in snapshot.children) {
                        val product = productSnapshot.getValue(Product::class.java)
                        listProduct.add(product!!)
                    }
                    itemRecyclerView.adapter = ProductAdapter(listProduct)
                }
                else{
                    Toast.makeText(applicationContext, "No Item Yet", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun deleteProduct(productName: String) {
        listProduct = arrayListOf<Product>()
        db_ref = FirebaseDatabase.getInstance().getReference("Product")

        db_ref.child(productName).removeValue().addOnSuccessListener {
            Toast.makeText(applicationContext, "Deleted 1 item", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(applicationContext, "Failed to delete, try again", Toast.LENGTH_LONG).show()
        }
    }
}