package com.example.winxenchantixshop.Activity.Product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Adapter.ItemAdapter
import com.example.winxenchantixshop.Adapter.ProductAdapter
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.R
import com.example.winxenchantixshop.databinding.ActivityCategoryDetailBinding
import com.google.firebase.database.*

class CategoryDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryDetailBinding
    private lateinit var productAdapter : ProductAdapter
    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var db_ref : DatabaseReference
    private lateinit var listProduct: ArrayList<Product>
    private lateinit var category: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val title = intent.getStringExtra("category")
        category = title!!.dropLast(8).toString()


        binding.categoryToolbar.setTitle(title)

        itemRecyclerView = binding.recycleViewListProduct
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemRecyclerView.setHasFixedSize(true)
        listProduct = arrayListOf<Product>()
        productAdapter = ProductAdapter(listProduct)
        getProduct()



    }

    private fun getProduct() {
        listProduct = arrayListOf<Product>()
        db_ref = FirebaseDatabase.getInstance().getReference("Product")
        db_ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (productSnapshot in snapshot.children) {
                        val product = productSnapshot.getValue(Product::class.java)
                        if (product!!.category.toString() == category){
                            listProduct.add(product)
                        }
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
}